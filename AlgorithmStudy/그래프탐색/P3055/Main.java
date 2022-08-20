package AlgorithmStudy.그래프탐색.P3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// * 와 S를 큐에 넣어두기 (순서는 * -> S)
// 큐에서 꺼내면서
// 1. * 라면 동서남북 확장해서 * 을 큐에 추가
// 2. S 라면 마찬가지로 갈 수 있는 곳에 S 추가 (만약 갈 수가 없게되면 추가 안되므로 사라짐)
// 3. S가 D 위치에 도달하면 timeStamp 출력, while 문 끝나고 나서는 KAKTUS 출력

// ++p.timeStamp 하게 되면 p의 timeStamp가 계속 증가하게 된다
public class Main {
    static int R, C;
    static char[][] map;
    static Queue<Position> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int desX = 0, desY = 0;
    static boolean[][] isVisited; // S 가 방문한 곳 check
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        isVisited=new boolean[R][C];
        int posX = 0, posY = 0;
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '*') q.add(new Position(i, j, '*'));
                else if (map[i][j] == 'S') {
                    posX = i;
                    posY = j;
                }
                else if(map[i][j]=='D'){
                    desX = i;
                    desY = j;
                }
            }
        }
        // S 큐에 넣기
        q.add(new Position(posX, posY, 'S', 0));
        isVisited[posX][posY]=true;
        int answer = bfs();
        if(answer!=-1) System.out.println(answer);
        else System.out.println("KAKTUS");

    }

    public static int bfs() {
        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            Position p = q.poll();
            // 2. 목적지인가? -> S 이면서 위치가 D에 있어야함
            if (p.item == 'S') {
                if (p.x==desX && p.y==desY) return p.timeStamp;
            }
            // 3. 연결된 곳 순회
            //      - * 인 경우
            //      - S 인 경우

            for (int d = 0; d < 4; d++) {
                int tempX = p.x + dx[d];
                int tempY = p.y + dy[d];
                if (p.item == '*') {
                    // 4. 갈 수 있는가? -> map 안에 존재 & 굴이 아니고 바위가 아니어야 함 + 물도 아니어야함(효율성 증가)
                    if ((tempX >= 0 && tempX < R) && (tempY >= 0 && tempY < C) && map[tempX][tempY] != 'D' && map[tempX][tempY] != 'X' && map[tempX][tempY]!='*') {
                        // 5. 간다
                        map[tempX][tempY] = '*';
                        // 6.큐에 넣기
                        q.add(new Position(tempX, tempY, '*'));
                    }
                } else if (p.item == 'S') {
                    // 4. 갈 수 있는가? -> map 안에 존재 & 바위가 아니고 물도 아니어야 함 + 왔던 곳으로 안감
                    if ((tempX >= 0 && tempX < R) && (tempY >= 0 && tempY < C) && map[tempX][tempY] != 'X' && map[tempX][tempY] != '*' && !isVisited[tempX][tempY]) {
                        // 5. 간다
//                        map[tempX][tempY] = 'S';
                        isVisited[tempX][tempY]=true;
                        // 6. 큐에 넣기
                        int time = p.timeStamp;
                        q.add(new Position(tempX, tempY, 'S', time+1));
                    }
                }
            }


        }
        return -1;
    }


}

class Position {
    int x, y;
    char item;
    int timeStamp;

    public Position(int x, int y, char item) {
        this.x = x;
        this.y = y;
        this.item = item;
    }

    public Position(int x, int y, char item, int timeStamp) {
        this.x = x;
        this.y = y;
        this.item = item;
        this.timeStamp = timeStamp;
    }
}
