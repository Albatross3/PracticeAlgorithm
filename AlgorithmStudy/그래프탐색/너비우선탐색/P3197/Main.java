package AlgorithmStudy.그래프탐색.너비우선탐색.P3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static List<int[]> swanPosition = new ArrayList<>();
    static int startX, startY;
    static int endX, endY;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Position> waterNearIce;
    static Queue<Position> swanExplored;
    static boolean[][] isVisited;
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // L 위치 찾기
        waterNearIce = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    int[] p = new int[]{i, j};
                    swanPosition.add(p);
                    waterNearIce.add(new Position('.', i, j));
                } else if (map[i][j] == '.') {
                    waterNearIce.add(new Position('.', i, j));
                }
            }
        }

        startX = swanPosition.get(0)[0];
        startY = swanPosition.get(0)[1];

        endX = swanPosition.get(1)[0];
        endY = swanPosition.get(1)[1];

        swanExplored = new LinkedList<>();
        isVisited = new boolean[R][C];
        isVisited[startX][startY] = true;
        swanExplored.add(new Position('L', startX, startY));

        int day = 0;
        while (!isSwanCanMeet()) {
            // map 변화
            changeMap();
            day++;
        }
        System.out.println(day);
    }

    public static boolean isSwanCanMeet() {
        while (!swanExplored.isEmpty()) {
            Position current = swanExplored.poll();
            if (current.x == endX && current.y == endY) return true;
            for (int d = 0; d < 4; d++) {
                int nextX = current.x + dx[d];
                int nextY = current.y + dy[d];
                if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || isVisited[nextX][nextY]) continue;
                isVisited[nextX][nextY] = true;
                if (map[nextX][nextY] == '.' || map[nextX][nextY] == 'L') {
                    swanExplored.add(new Position('L', nextX, nextY));
                } else if (map[nextX][nextY] == 'X') {
                    q.add(new Position('L', nextX, nextY));
                }
            }
        }


        while (!q.isEmpty()) {
            swanExplored.add(q.poll());
        }

        return false;
    }

    public static void changeMap() {
        int i = 0;
        int size = waterNearIce.size();
        while (i < size) {
            Position water = waterNearIce.poll();
            for (int d = 0; d < 4; d++) {
                int nextX = water.x + dx[d];
                int nextY = water.y + dy[d];
                if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) continue;
                if (map[nextX][nextY] == 'X') {
                    map[nextX][nextY] = '.';
                    waterNearIce.add(new Position('.', nextX, nextY));
                }
            }
            i++;
        }
    }

}
class Position {
    char c;
    int x;
    int y;

    public Position(char c, int x, int y) {
        this.c = c;
        this.x = x;
        this.y = y;
    }
}
