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
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//
//        for (int i = 0; i < N; i++) {
//            String s = br.readLine();
//            for (int j = 0; j < M; j++) {
//                int value = s.charAt(j) - '0';
//                map[i][j] = value;
//            }
//        }
        String x = "  abv abc  ";
        String[] split = x.split("[a-zA-Z]+");
        System.out.println(split.length);
        System.out.println(Character.toLowerCase('e'));

//        System.out.println(bfs());

    }

    private static int bfs() {
        Cor start = new Cor(0, 0, 1, false);
        boolean[][] isVisited = new boolean[N][M];
        isVisited[0][0] = true;
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
            // 벽을 뚫지 않았을 때
            if(!c.isCrashed) {
                for (int d = 0; d < 4; d++) {
                    int newX = c.x + dx[d];
                    int newY = c.y + dy[d];
                    // 4. 갈 수 있는가?
                    if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                    if (isVisited[newX][newY]) continue;

                    if (map[newX][newY] == 0) {
                        isVisited[newX][newY] = true;
                        q.add(new Cor(newX, newY, c.move + 1, c.isCrashed, c.crashedX, c.crashedY, c.isVisitedByCrash));
                    } else if (map[newX][newY] == 1) {
                        boolean[][] temp = new boolean[N][M];
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < M; j++) {
                                temp[i][j] = isVisited[i][j];
                            }
                        }
                        temp[newX][newY] = true;
                        q.add(new Cor(newX, newY, c.move + 1, true, newX, newY, temp));
                    }
                }
            }
            // 벽을 뚫었을 때
            else {
                for (int d = 0; d < 4; d++) {
                    int newX = c.x + dx[d];
                    int newY = c.y + dy[d];
                    // 4. 갈 수 있는가?
                    if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                    if (c.isVisitedByCrash[newX][newY]) continue;

                    if (map[newX][newY] == 0) {
                        c.isVisitedByCrash[newX][newY] = true;
                        q.add(new Cor(newX, newY, c.move + 1, c.isCrashed, c.crashedX, c.crashedY, c.isVisitedByCrash));
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
    // 부서진 좌표
    int crashedX;
    int crashedY;
    boolean[][] isVisitedByCrash;

    public Cor(int x, int y, int move, boolean isCrashed) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.isCrashed = isCrashed;
        this.crashedX = -1;
        this.crashedY = -1;
        this.isVisitedByCrash = null;
    }

    public Cor(int x, int y, int move, boolean isCrashed, int crashedX, int crashedY, boolean[][] isVisitedByCrash) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.isCrashed = isCrashed;
        this.crashedX = crashedX;
        this.crashedY = crashedY;
        this.isVisitedByCrash = isVisitedByCrash;
    }
}
