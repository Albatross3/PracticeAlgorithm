package SAMSUNG.하반기_2015.문제2;

// 중복되는 코드가 있을 때는 메서드로 만들기
// simulation 에서 가지치기로 없앨 수 있다면 최대한 없애기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static Queue<Position> queueR = new LinkedList<>();
    static Queue<Position> queueB = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'R') {
                    queueR.add(new Position(i, j, 0));
                } else if (map[i][j] == 'B') {
                    queueB.add(new Position(i, j, 0));
                }
            }
        }

        System.out.println(bfs());

    }

    public static int bfs() {
        // bfs -> 모든 경우의 수
        while (!queueR.isEmpty() && !queueB.isEmpty()) {
            // 1. 큐에서 꺼내기
            Position currentR = queueR.poll();
            Position currentB = queueB.poll();
            // 2. 목적지인가?
            if (currentR.count > 10) return -1;
            if (map[currentB.x][currentB.y] == 'O') continue;
            else {
                if (map[currentR.x][currentR.y] == 'O') return currentR.count;
            }
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                // R 의 미끄러지기
                int nextR_X = currentR.x;
                int nextR_Y = currentR.y;
                while (map[nextR_X + dx[d]][nextR_Y + dy[d]] != '#') {
                    nextR_X += dx[d];
                    nextR_Y += dy[d];
                    if (map[nextR_X][nextR_Y] == 'O') break;
                }

                // B 의 미끄러지기
                int nextB_X = currentB.x;
                int nextB_Y = currentB.y;
                while (map[nextB_X+dx[d]][nextB_Y+dy[d]] != '#') {
                    nextB_X += dx[d];
                    nextB_Y += dy[d];
                    if (map[nextB_X][nextB_Y] == 'O') break;
                }

                // R 과 B 의 위치가 같은 경우 재조정
                // O 에 빠진 경우에도 고려 필요
                if (nextR_X == nextB_X && nextR_Y == nextB_Y && map[nextR_X][nextR_Y] != 'O') {
                    // 북
                    if (d == 0 && currentR.x < currentB.x) {
                        nextB_X = nextR_X + 1;
                    }
                    if (d == 0 && currentR.x > currentB.x) {
                        nextR_X = nextB_X + 1;
                    }
                    // 동
                    if (d == 1 && currentR.y < currentB.y) {
                        nextR_Y = nextB_Y - 1;
                    }
                    if (d == 1 && currentR.y > currentB.y) {
                        nextB_Y = nextR_Y - 1;
                    }
                    // 남
                    if (d == 2 && currentR.x < currentB.x) {
                        nextR_X = nextB_X - 1;
                    }
                    if (d == 2 && currentR.x > currentB.x) {
                        nextB_X = nextR_X - 1;
                    }
                    // 서
                    if (d == 3 && currentR.y < currentB.y) {
                        nextB_Y = nextR_Y + 1;
                    }
                    if (d == 3 && currentR.y > currentB.y) {
                        nextR_Y = nextB_Y + 1;
                    }
                }
                // 큐에 넣기
                if(nextR_X==currentR.x && nextR_Y == currentR.y && nextB_X == currentB.x && nextB_Y == currentB.y) continue;
                queueR.add(new Position(nextR_X, nextR_Y, currentR.count + 1));
                queueB.add(new Position(nextB_X, nextB_Y, currentB.count + 1));
            }
        }
        return -1;

    }
}


class Position {
    int x;
    int y;
    int count;

    public Position(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}