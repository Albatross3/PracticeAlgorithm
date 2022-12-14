package AlgorithmStudy.그래프탐색.P14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<Coordinate> coordinateSet = new ArrayList<>();
    static int max = 0;

    static int[] number;
    static int[] temp;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];

        coordinateSet.add(new Coordinate(-1, -1));
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    coordinateSet.add(new Coordinate(i, j));
                }
            }
        }


        // backtracking 을 위한 준비
        number = new int[coordinateSet.size()];
        for (int i = 0; i < number.length; i++) {
            number[i] = i;
        }
        isVisited = new boolean[coordinateSet.size()];
        temp = new int[3 + 1];

        backtracking(1);

        System.out.println(max);

    }

    public static void backtracking(int depth) {
        if (depth == 4) {
            max = Math.max(max, calculate(temp[1], temp[2], temp[3]));
            return;
        }
        for (int i = 0; i < number.length; i++) {
            if (!isVisited[i] && temp[depth-1] < number[i]) {
                isVisited[i] = true;
                temp[depth] = number[i];
                backtracking(depth + 1);
                isVisited[i] = false;
            }
        }
    }

    public static int calculate(int index1, int index2, int index3) {
        // map 과 같은 형태를 가짐
        int[][] tempMap = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        Coordinate wall1 = coordinateSet.get(index1);
        Coordinate wall2 = coordinateSet.get(index2);
        Coordinate wall3 = coordinateSet.get(index3);

        // 벽 세우기
        tempMap[wall1.x][wall1.y] = 1;
        tempMap[wall2.x][wall2.y] = 1;
        tempMap[wall3.x][wall3.y] = 1;

        // 바이러스 퍼뜨리기
        boolean[][] isVirusVisited = new boolean[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (tempMap[i][j] == 2 && !isVirusVisited[i][j]) {
                    proliferate(tempMap, isVirusVisited, i, j);
                }
            }
        }

        // 0의 개수 세기
        int safeArea = 0;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (tempMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    public static void proliferate(int[][] array, boolean[][] isVirusVisited,int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Coordinate> queue = new LinkedList<>();

        queue.add(new Coordinate(x, y));

        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내기
            Coordinate c = queue.poll();
            // 2. 목적지인가?
            array[c.x][c.y] = 2;
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = c.x + dx[d];
                int tempY = c.y + dy[d];
                // 4. 갈 수 있는가? -> map 안에 존재 && 방문안한곳 && 2나 0인 곳
                if (tempX >= 1 && tempX <= N && tempY >= 1 && tempY <= M && !isVirusVisited[tempX][tempY] && (array[tempX][tempY] == 0 || array[tempX][tempY] == 2)) {
                    // 5. 간다
                    isVirusVisited[tempX][tempY] = true;
                    // 6. 큐에 넣기
                    queue.add(new Coordinate(tempX, tempY));
                }
            }
        }

    }
}
class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}