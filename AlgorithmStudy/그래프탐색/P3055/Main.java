package AlgorithmStudy.그래프탐색.P3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// <초기 설계>
// * 와 S를 큐에 넣어두기 (순서는 * -> S)
// 큐에서 꺼내면서
// 1. * 라면 동서남북 확장해서 * 을 큐에 추가
// 2. S 라면 마찬가지로 갈 수 있는 곳에 S 추가 (만약 갈 수가 없게되면 추가 안되므로 사라짐)
// 3. S가 D 위치에 도달하면 timeStamp 출력, while 문 끝나고 나서는 KAKTUS 출력

// ++p.timeStamp 하게 되면 p의 timeStamp가 계속 증가하게 된다

// <refactoring 후 설계>
// 최단 거리이므로 dp[][] 를 이용하는 것이 효과적
// 큐에 들어갈 수 있는 녀석들 -> *, . , S, D
public class Main {
    static int R, C;
    static char[][] map;
    static Queue<Position> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dp;
    static boolean isExist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dp = new int[R][C];
        isExist = false;
        Position start = null;
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '*') q.add(new Position(i, j, '*'));
                else if (map[i][j] == 'S') start = new Position(i, j, 'S');
            }
        }
        // S 큐에 넣기
        q.add(start);

        // bfs 돌기
        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            Position p = q.poll();
            // 2. 목적지인가?
            if (p.type == 'D') {
                System.out.println(dp[p.x][p.y]);
                isExist = true;
                break;
            }
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = p.x + dx[d];
                int tempY = p.y + dy[d];
                // 4. 갈 수 있는가?
                if ((tempX >= 0 && tempX < R) && (tempY >= 0 && tempY < C)) {
                    // . 또는 D 인 경우
                    if (p.type == '.' || p.type == 'S') {
                        if ((map[tempX][tempY] == '.' || map[tempX][tempY] == 'D') && dp[tempX][tempY] == 0) {
                            // 5. 간다
                            dp[tempX][tempY] = dp[p.x][p.y] + 1;
                            // 6. 큐에 넣기
                            q.add(new Position(tempX, tempY, map[tempX][tempY]));
                        }
                    }
                    // * 인 경우
                    else if (p.type == '*') {
                        if (map[tempX][tempY] == '.' || map[tempX][tempY] == 'S') {
                            // 5. 간다
                            map[tempX][tempY] = '*';
                            // 6. 큐에 넣기
                            q.add(new Position(tempX, tempY, '*'));
                        }
                    }
                }
            }
        }
        if(!isExist) System.out.println("KAKTUS");
    }

}

class Position {
    int x, y;
    char type;

    public Position(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

}
