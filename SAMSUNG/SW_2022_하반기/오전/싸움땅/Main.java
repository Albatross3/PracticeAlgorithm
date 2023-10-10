package SAMSUNG.SW_2022_하반기.오전.싸움땅;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static PriorityQueue<Integer>[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] playerPosition;
    static Player[] players;
    static int[] scores;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new PriorityQueue[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = new PriorityQueue<>((o1,o2) -> o2-o1); // 오름차순 pq
                int g = Integer.parseInt(st.nextToken());
                if(g!=0) map[i][j].add(g);
            }
        }
        playerPosition = new int[n][n];
        players = new Player[m+1];
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            playerPosition[x][y] = i;
            players[i] = new Player(x,y,d,s,0);
        }
        scores = new int[m+1];

        while(k-->0) {
            for(int i=1; i<players.length; i++) {
                gameByEachPlayer(i);
            }
        }
        printScores();
    }

    static void gameByEachPlayer(int playerIndex) {
        Player currentPlayer = players[playerIndex];
        int dir = currentPlayer.d;
        int x = currentPlayer.x;
        int y = currentPlayer.y;
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        // 격자 벗어나면 정반대 방향으로 바꿈
        if(nx<0 || nx>=n || ny<0 || ny>=n) {
            dir = (currentPlayer.d+2)%4;
            nx = x + dx[dir];
            ny = y + dy[dir];
            currentPlayer.d = dir;
        }

        if(playerPosition[nx][ny] == 0) {
            // 2-1 플레이어 없는 경우 : 총이 있으면 총 획득
            // 플레이어 객체의 위치 변경
            currentPlayer.x = nx;
            currentPlayer.y = ny;
            // 이동 할때 플레이어 위치 map 도 변경해야 -> 기존 위치는 0
            playerPosition[x][y] = 0;
            playerPosition[nx][ny] = playerIndex;
            // 총 있으면 총 획득
            getGun(playerIndex, nx, ny);
        } else {
            // 2-2 플레이어 있는 경우
            playerPosition[x][y] = 0; // 현재 위치는 0으로
            int rival = playerPosition[nx][ny];
            fight(playerIndex, rival);
        }

    }

    static void getGun(int playerIndex, int x, int y) {
        if(!map[x][y].isEmpty()) {
            if(players[playerIndex].gunPower==0) {
                players[playerIndex].gunPower = map[x][y].poll();
            } else {
                map[x][y].add(players[playerIndex].gunPower);
                players[playerIndex].gunPower = map[x][y].poll();
            }
        }
    }

    static void fight(int playerIndex, int rival) {
        // 1) 싸움
        int[] result = getWinnerAndLoser(playerIndex, rival);
        int win = result[0];
        int lose = result[1];
        // 2) 진 플레이어 총 내려놓기, 이동, 총 있으면 총 획득
        int curX = players[rival].x;
        int curY = players[rival].y;
        loseBehavior(lose, curX, curY);
        // 3) 이긴 플레이어 총 있으면 총 획득
        winBehavior(win, curX, curY);
    }

    static int[] getWinnerAndLoser(int playerIndex, int rival) {
        Player p1 = players[playerIndex];
        Player p2 = players[rival];
        int win;
        int lose;
        if(p1.s + p1.gunPower > p2.s + p2.gunPower) {
            win = playerIndex;
            lose = rival;
            scores[playerIndex] += (p1.s + p1.gunPower) - (p2.s + p2.gunPower);
        } else if(p1.s + p1.gunPower < p2.s + p2.gunPower) {
            win = rival;
            lose = playerIndex;
            scores[rival] += (p2.s + p2.gunPower) - (p1.s + p1.gunPower);
        } else {
            if(p1.s > p2.s) {
                win = playerIndex;
                lose = rival;
            } else {
                win = rival;
                lose = playerIndex;
            }
        }
        return new int[]{win, lose};
    }

    static void loseBehavior(int playerIndex, int x, int y) {
        // 진 플레이어 총 내려놓기
        Player losePlayer = players[playerIndex];
        map[x][y].add(losePlayer.gunPower);
        losePlayer.gunPower = 0;

        // 진 플레이어 이동하기
        for(int i=0; i<4; i++) {
            int nd = (losePlayer.d + i)%4;
            int nx = x + dx[nd];
            int ny = y + dy[nd];
            if(nx<0 || nx>=n || ny<0 || ny>=n || playerPosition[nx][ny]!=0) continue;
            // 해당 위치로 이동
            losePlayer.x = nx;
            losePlayer.y = ny;
            losePlayer.d = nd;
            playerPosition[nx][ny] = playerIndex;
            break;
        }

        // 진 플레이어 총 획득
        getGun(playerIndex, losePlayer.x, losePlayer.y);
    }

    static void winBehavior(int playerIndex, int x, int y) {
        Player winPlayer = players[playerIndex];
        playerPosition[x][y] = playerIndex;
        winPlayer.x = x;
        winPlayer.y = y;
        getGun(playerIndex, x, y);
    }

    static class Player {
        int x;
        int y;
        int d;
        int s;
        int gunPower;

        public Player(int x, int y, int d, int s, int gunPower) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.gunPower = gunPower;
        }
    }

    static void printScores() {
        for(int i=1; i<=m; i++) {
            System.out.print(scores[i]+" ");
        }
    }
}
