package AlgorithmStudy;
// 백준 2206번
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
        map = new int[N + 1][M + 1];

        int xx =3;
        for (int i = 1; i < N + 1; i++) {
            String s = br.readLine();
            for (int j = 1; j < M + 1; j++) {
                int value = s.charAt(j - 1) - '0';
                map[i][j] = value;
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        boolean[][] isVisited = new boolean[N + 1][M + 1];
        Queue<Cor> queue = new LinkedList<>();

        queue.add(new Cor(1, 1,1,false));
        isVisited[1][1] = true;

        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내기
            Cor c = queue.poll();
            // 2. 목적지인가?
            if(c.x==N && c.y==M) {
                return c.move;
            }

            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = c.x + dx[d];
                int tempY = c.y + dy[d];
                // 4. 갈 수 있는가? -> map 안에 존재 & 가지 않은 곳 & map 에서 0
                if (c.isCrashed) {
                    if (tempX >= 1 && tempX <= N && tempY >= 1 && tempY <= M && !isVisited[tempX][tempY] && map[tempX][tempY] == 0) {
                        // 5. 간다
                        isVisited[tempX][tempY] = true;
                        // 6. 큐에 넣기
                        queue.add(new Cor(tempX, tempY, c.move+1, true));
                    }
                } else {
                    if (tempX >= 1 && tempX <= N && tempY >= 1 && tempY <= M && !isVisited[tempX][tempY] && map[tempX][tempY] == 0) {
                        // 5. 간다
                        isVisited[tempX][tempY] = true;
                        // 6. 큐에 넣기
                        queue.add(new Cor(tempX, tempY, c.move+1, false));
                    }
                    if (tempX >= 1 && tempX <= N && tempY >= 1 && tempY <= M && !isVisited[tempX][tempY] && map[tempX][tempY] == 1) {
                        // 5. 간다
                        isVisited[tempX][tempY] = true;
                        // 6. 큐에 넣기
                        queue.add(new Cor(tempX, tempY, c.move+1, true));
                    }
                }

            }

        }

        return -1;
    }
}
class Cor{
    int x;
    int y;
    int move;
    boolean isCrashed; // 경로에서 1번이라도 벽을 부쉈는지 확인

    public Cor(int x, int y, int move, boolean isCrashed) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.isCrashed = isCrashed;
    }
}
