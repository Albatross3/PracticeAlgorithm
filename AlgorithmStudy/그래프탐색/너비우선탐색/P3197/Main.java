package AlgorithmStudy.그래프탐색.너비우선탐색.P3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<int[]> swanInitPosition = new ArrayList<>();
    static Queue<Cor> waterQueue = new LinkedList<>();
    static Queue<Cor> swanQueue = new LinkedList<>();
    static boolean[][] isVisited;
    static int startX, startY, endX, endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 백조 1, 2 위치 찾기 + waterQueue 에 '.' 위치 넣기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    int[] p = new int[]{i, j};
                    swanInitPosition.add(p);
                    waterQueue.add(new Cor(i, j));
                } else if (map[i][j] == '.') {
                    waterQueue.add(new Cor(i, j));
                }
            }
        }

        startX = swanInitPosition.get(0)[0];
        startY = swanInitPosition.get(0)[1];
        endX = swanInitPosition.get(1)[0];
        endY = swanInitPosition.get(1)[1];

        // 최적화된 bfs 탐색 -> map 변화
        int day = 0;
        swanQueue.add(new Cor(startX, startY));
        isVisited = new boolean[R][C];
        isVisited[startX][startY] = true;

        while (!swanCanMeet()) {
            iceMelt();
            day++;
        }
        System.out.println(day);
    }

    public static boolean swanCanMeet() {
        Queue<Cor> nextSwanQueue = new LinkedList<>();
        while (!swanQueue.isEmpty()) {
            Cor swanCurrent = swanQueue.poll();
            if (swanCurrent.x == endX && swanCurrent.y == endY) return true;
            for (int d = 0; d < 4; d++) {
                int nextX = swanCurrent.x + dx[d];
                int nextY = swanCurrent.y + dy[d];
                if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || isVisited[nextX][nextY]) continue;
                isVisited[nextX][nextY] = true;
                if (map[nextX][nextY] == 'X') {
                    nextSwanQueue.add(new Cor(nextX, nextY));
                } else {
                    swanQueue.add(new Cor(nextX, nextY));
                }
            }
        }
        swanQueue = nextSwanQueue;
        return false;
    }

    public static void iceMelt() {
        Queue<Cor> nextWaterQueue = new LinkedList<>();
        while (!waterQueue.isEmpty()) {
            Cor waterCurrent = waterQueue.poll();
            for (int d = 0; d < 4; d++) {
                int nextX = waterCurrent.x + dx[d];
                int nextY = waterCurrent.y + dy[d];
                if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || isVisited[nextX][nextY]) continue;
                if (map[nextX][nextY] == 'X') {
                    map[nextX][nextY] = '.';
                    nextWaterQueue.add(new Cor(nextX, nextY));
                }
            }
        }
        waterQueue = nextWaterQueue;
    }
}
class Cor {
    int x;
    int y;

    public Cor(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
