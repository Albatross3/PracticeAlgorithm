package SAMSUNG_SDS.상반기_2022.문제3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Cor[][] map;
    static boolean[][] isVisited;

    static ArrayList<Deque<Cor>> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new Cor[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = new Cor(i, j, Integer.parseInt(st.nextToken()), 0);
            }
        }
        isVisited = new boolean[n][n];

        // dfs 돌리면서 각 팀의 Deque 에 넣기
        for (int i = 0; i <= m; i++) {
            arr.add(new LinkedList<>());
        }
        int index = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j] && map[i][j].type != 0) {
                    dfs(i, j, arr.get(index));
                    index++;
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (Cor c : arr.get(i)) {
                System.out.print(c.type + " ");
            }
            System.out.println();
        }
        // 시뮬레이션
        int round = 1;
        int result = 0;
        while (round <= k) {
            // 1. 머리사람을 따라서 한 칸 이동
            // 2. 라운드마다 정해진 선에 따라 공 던져짐
            // 3. 최초에 만나는 사람이 공을 얻어서 점수를 얻게 됨

            round++;
        }
    }

    public static void dfs(int x, int y, Deque<Cor> deque) {
        // 1. 체크인
        isVisited[x][y] = true;
        // 2. 목적지인가? -> Cor 객체를 덱에 넣기
        deque.add(map[x][y]);
        // 3. 연결된 곳 순회
        for (int d = 0; d < 4; d++) {
            int tempX = x + dx[d];
            int tempY = y + dy[d];
            // 4. 갈 수 있는가? -> map 안에 존재 & 방문하지 않은 곳 & map 에서 0 이 아닌 곳
            if (tempX >= 0 && tempX < n && tempY >= 0 && tempY < n && !isVisited[tempX][tempY] && map[tempX][tempY].type != 0) {
                // 5. 간다
                dfs(tempX, tempY, deque);
            }
        }
    }
}
class Cor {
    int x;
    int y;
    int type; // 1 2 3 4 0
    int order;

    public Cor(int x, int y, int type, int order) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.order = order;
    }
}
