package AlgorithmStudy;
// P 3197 - 백조의 호수
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        Position s=null;
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j]=='L'){
                    s = new Position(i, j, 'M'); // 2번째에 만나는 'L'
                }
            }
        }

        // 1. L이 도달 가능한지 확인 -> bfs()
        // 2. 도달 가능하지 않으면 반복
        int count = 0;
        while (!isReachable(s)) {
            boolean[][] sholudChanged = new boolean[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'X') {
                        for (int d = 0; d < 4; d++) {
                            int tempX = i + dx[d];
                            int tempY = j + dy[d];
                            // 4. 갈 수 있는가? -> 경계 안에 존재 & . 또는 L 인 경우
                            if (tempX >= 0 && tempX < R && tempY >= 0 && tempY < C && map[tempX][tempY]=='.') {
                                sholudChanged[i][j]=true;
                                break;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(sholudChanged[i][j])
                        map[i][j] = '.';
                }
            }
            count++;
        }
        System.out.println(count);
    }

    public static boolean isReachable(Position start) {
        Queue<Position> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[R][C];
        q.add(start);
        isVisited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            Position p = q.poll();
            // 2. 목적지인가?
            if(p.type=='L') return true;
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = p.x + dx[d];
                int tempY = p.y + dy[d];
                // 4. 갈 수 있는가? -> 경계 안에 존재 & . 또는 L 인 경우
                if (tempX >= 0 && tempX < R && tempY >= 0 && tempY < C && !isVisited[tempX][tempY]) {
                    if (map[tempX][tempY] == '.' || map[tempX][tempY] == 'L') {
                        // 5. 간다
                        isVisited[tempX][tempY]=true;
                        // 6. 큐에 넣기
                        q.add(new Position(tempX, tempY, map[tempX][tempY]));
                    }
                }
            }
        }
        return false;
    }
}
class Position{
    int x, y;
    char type;

    public Position(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
