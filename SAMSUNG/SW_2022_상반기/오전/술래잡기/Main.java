package SAMSUNG.SW_2022_상반기.오전.술래잡기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n,m,h,k;
    static int[][] map; // 나무만 존재 나무 : -1
    static int[] dx = {0,1,0,-1}; // 동 남 서 북
    static int[] dy = {1,0,-1,0}; // 동 남 서 북
    static Queue<Person> runners;
    static Person it;
    static Set<Integer> turnTiming;
    static int score = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        runners = new LinkedList<>();
        it = new Person(n/2, n/2, 3);
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1; // 1 : 좌우 , 2 : 상하
            Person p = new Person(x, y, d);
            runners.add(p);
        }
        for(int i=0; i<h; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            map[x][y] = -1;
        }

        getTurnTiming();

        for(int t=1; t<=k; t++) {

            // 1. 도망자들 동시에 이동
            moveRunners();
            // 2. 술래 이동
            int turn = t%(n*n*2-2)==0 ? n*n*2-2 : t%(n*n*2-2);
            moveIt(turn);
            addScore(t);

        }
        System.out.println(score);
    }

    static void getTurnTiming() {
        // 술래 방향 꺾이는 지점
        int sum = 0;
        turnTiming = new HashSet<>();

        for(int i=1; i<n; i++) {
            for(int j=0; j<2; j++) {
                sum += i;
                turnTiming.add(sum);
            }
        }

        for(int j=0; j<2; j++) {
            sum += n-1;
            turnTiming.add(sum);
        }

        for(int i=n-1; i>0; i--) {
            for(int j=0; j<2; j++) {
                sum += i;
                turnTiming.add(sum);
            }
        }
    }

    static void moveRunners() {
        for(Person runner : runners) {
            int distance = getDistanceFromIt(runner);
            if(distance <= 3) {
                int nx = runner.x + dx[runner.d];
                int ny = runner.y + dy[runner.d];
                if(nx>=0 && nx<n && ny>=0 && ny<n) {
                    if(nx != it.x || ny != it.y) {
                        runner.x = nx;
                        runner.y = ny;
                    }
                } else {
                    runner.d = (runner.d + 2)%4;
                    nx = runner.x + dx[runner.d];
                    ny = runner.y + dy[runner.d];
                    if(nx != it.x || ny != it.y) {
                        runner.x = nx;
                        runner.y = ny;
                    }
                }
            }
        }
    }

    private static int getDistanceFromIt(Person runner) {
        return Math.abs(runner.x-it.x) + Math.abs(runner.y-it.y);
    }

    static void moveIt(int turn) {
        it.x = it.x + dx[it.d];
        it.y = it.y + dy[it.d];
        // 시계방향 이동
        if(turn<n*n-1) {
            if(turnTiming.contains(turn)) {
                it.d = (it.d+1)%4;
            }
        }
        // 다음 위치 (0,0)인 경우
        else if(turn == n*n-1) {
            it.d = (it.d+2)%4;
        }
        // 반시계방향 이동
        else if(turn < n*n*2-2) {
            if(turnTiming.contains(turn)) {
                it.d = it.d-1 == -1 ? 3 : it.d-1;
            }
        }
        // 다음 위치 정중앙인 경우
        else if(turn == n*n*2-2) {
            it.d = (it.d+2)%4;
        }
    }

    static void addScore(int turn) {
        for(int l=0; l<3; l++) {
            int nx = it.x + l*dx[it.d];
            int ny = it.y + l*dy[it.d];
            if(nx<0 || nx>=n || ny<0|| ny>=n) continue;
            if(map[nx][ny]==-1) continue;
            score += turn*findRunnerAndRemove(nx,ny);
        }
    }

    static private int findRunnerAndRemove(int x, int y) {
        int count = 0;
        int size = runners.size();
        for(int i=0; i<size; i++) {
            Person runner = runners.poll();
            if(runner.x == x && runner.y == y) {
                count++;
            } else {
                runners.add(runner);
            }
        }
        return count;
    }

    static class Person {
        int x;
        int y;
        int d; // 0,1,2,3 -> 동 남 서 북

        public Person(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
