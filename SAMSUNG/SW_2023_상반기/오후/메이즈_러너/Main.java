package SAMSUNG.SW_2023_상반기.오후.메이즈_러너;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N ,M, K;
    static int[][] map;
    static List<Cor> runner;
    static Cor exit;
    static int[] dx = {-1,1,0,0}; // 위 아래 오른 왼
    static int[] dy = {0,0,1,-1}; // 위 아래 오른 왼
    static int movedDistance = 0;
    static int[] point = new int[2];
    static int D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        runner = new ArrayList<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            runner.add(new Cor(x-1, y-1));
        }
        st = new StringTokenizer(br.readLine());
        exit = new Cor(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        while(K-- > 0) {
            // 모든 참가자 이동
            move();
            // 이동 후 모든 참가자들이 탈출한다면 break
            if(runner.isEmpty()) break;
            // 가장 작은 정사각형 찾기
            findSquare();
            // 회전
            rotate();
        }
        System.out.println(movedDistance);
        System.out.print((exit.x+1) + " " + (exit.y+1));
    }

    public static void move() {
        // 모든 참가자 이동
        for(Cor r : runner) {
            int shortestDistance = getShortestDistance(r.x, r.y);
            for(int d=0; d<4; d++) {
                int nx = r.x + dx[d];
                int ny = r.y + dy[d];
                // map 안에 존재 && 빈 벽 && 가까워져야함
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(map[nx][ny]!=0) continue;
                int nextShortestDistance = getShortestDistance(nx, ny);
                if(nextShortestDistance < shortestDistance) {
                    r.x = nx;
                    r.y = ny;
                    movedDistance++;
                    break;
                }
            }
        }
        // 이동 후 도착한 참가자 삭제
        Queue<Cor> q = new LinkedList<>(runner);
        runner = new ArrayList<>();
        while(!q.isEmpty()) {
            Cor r = q.poll();
            if(r.x == exit.x && r.y == exit.y) continue;
            runner.add(r);
        }
    }

    private static int getShortestDistance(int x, int y) {
        return Math.abs(exit.x-x) + Math.abs(exit.y-y);
    }

    public static void findSquare() {
        int d = 2;
        while(!isFind(d)) {
            d++;
        }
        D = d;
    }

    private static boolean isFind(int d) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int minX = i;
                int minY = j;
                int maxX = i+d-1;
                int maxY = j+d-1;
                if(exit.x < minX || exit.x > maxX || exit.y < minY || exit.y > maxY) continue;
                for(Cor c : runner) {
                    if(minX <= c.x && c.x <= maxX && minY <= c.y && c.y <= maxY) {
                        point[0]=i;
                        point[1]=j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void rotate() {
        int[][] temp = new int[D][D];
        // map 90도 시계방향 회전
        for(int i=point[0]; i<point[0]+D; i++) {
            for(int j=point[1]; j<point[1]+D; j++) {
                temp[j-point[1]][D-1-(i-point[0])] = map[i][j];
            }
        }
        // temp 배열 옮기기
        for(int i=0; i<D; i++) {
            for(int j=0; j<D; j++) {
                if(temp[i][j]!=0) map[i+point[0]][j+point[1]] = temp[i][j]-1;
                else map[i+point[0]][j+point[1]] = temp[i][j];
            }
        }

        int minX = point[0];
        int maxX = point[0]+D-1;
        int minY = point[1];
        int maxY = point[1]+D-1;
        for(Cor c:runner) {
            if(minX <= c.x && c.x <= maxX && minY <= c.y && c.y <= maxY) {
                int prevX = c.x;
                int prevY = c.y;
                c.x = prevY-point[1]+point[0];
                c.y = D-1-(prevX-point[0])+point[1];
            }
        }
        // 출구 확인
        if(minX <= exit.x && exit.x <= maxX && minY <= exit.y && exit.y <= maxY) {
            int prevX = exit.x;
            int prevY = exit.y;
            exit.x = prevY-point[1]+point[0];
            exit.y = D-1-(prevX-point[0])+point[1];
        }
    }

    static class Cor {
        int x;
        int y;

        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

