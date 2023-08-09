package Baekjoon.그래프탐색.너비우선탐색.P2206;

// 조건에 따라서 방문 배열을 3차원으로 구성해야 했다
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int value = s.charAt(j) - '0';
                map[i][j] = value;
            }
        }


        System.out.println(bfs());

    }

    private static int bfs() {
        Cor start = new Cor(0, 0, 1, false);
        // 0 -> 벽을 부수지 않은 경우 , 1 -> 벽을 부순 않은 경우
        boolean[][][] isVisited = new boolean[2][N][M];
        isVisited[0][0][0] = true;
        Queue<Cor> q = new LinkedList<>();
        q.add(start);


        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            Cor c = q.poll();
            // 2. 목적지인가?
            if (c.x == N - 1 && c.y == M - 1) {
                return c.move;
            }
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int nextX = c.x + dx[d];
                int nextY = c.y + dy[d];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                // 벽이 아닌 경우
                if (map[nextX][nextY] == 0) {
                    // 부수지 않은 경우
                    if(!c.isDestroyed && !isVisited[0][nextX][nextY]) {
                        isVisited[0][nextX][nextY] = true;
                        q.add(new Cor(nextX, nextY, c.move + 1, false));
                    }
                    // 부순 경우
                    else if(c.isDestroyed && !isVisited[1][nextX][nextY]){
                        isVisited[1][nextX][nextY] = true;
                        q.add(new Cor(nextX, nextY, c.move + 1, true));
                    }
                }
                // 벽인 경우
                else if (map[nextX][nextY] == 1) {
                    // 부수지 않은 경우에만 진행
                    if (!c.isDestroyed) {
                        isVisited[0][nextX][nextY] = true;
                        q.add(new Cor(nextX, nextY, c.move + 1, true));
                    }
                }

            }

        }
        return -1;
    }
}
class Cor {
    int x;
    int y;
    int move;
    boolean isDestroyed; // 경로에서 1번이라도 벽을 부쉈는지 확인

    public Cor(int x, int y, int move, boolean isDestroyed) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.isDestroyed = isDestroyed;
    }
}