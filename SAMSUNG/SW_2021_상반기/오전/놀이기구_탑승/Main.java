package SAMSUNG.SW_2021_상반기.오전.놀이기구_탑승;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] score = {0,1,10,100,1000};
    static Map<Integer, Set<Integer>> information;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        information = new LinkedHashMap<>();
        for(int i=0; i<n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            Set<Integer> set = new HashSet<>();
            for(int j=0; j<4; j++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            information.put(p, set);
        }

        map = new int[n][n];
        for(int currentStudent : information.keySet()) {
            // map 돌면서 위치 찾기
            Cor position = getPosition(currentStudent);
            map[position.x][position.y] = currentStudent;
        }
        // 점수 계산
        int jumsu = calculation();
        System.out.println(jumsu);

    }

    public static void print() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    static Cor getPosition(int currentStudent) {
        PriorityQueue<Cor> pq = new PriorityQueue<>();
        for(int r=0; r<n; r++) {
            for(int c=0; c<n; c++) {
                // 비어있는지 확인
                if(map[r][c]!=0) continue;
                int numOfFavorite = 0;
                int numOfEmpty = 0;
                for(int d=0; d<4; d++) {
                    int nx = r + dx[d];
                    int ny = c + dy[d];
                    // 갈 수 있는지
                    if(nx <0 || nx >=n || ny<0 || ny>=n) continue;
                    if(map[nx][ny]==0) numOfEmpty++;
                    else {
                        if(information.get(currentStudent).contains(map[nx][ny])) {
                            numOfFavorite++;
                        }
                    }
                }
                Cor cor = new Cor(r,c,numOfFavorite, numOfEmpty);
                pq.add(cor);
            }
        }
        return pq.poll();
    }

    static int calculation() {
        int sum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int count = 0;
                for(int d=0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(nx <0 || nx >=n || ny<0 || ny>=n) continue;
                    if(information.get(map[i][j]).contains(map[nx][ny])) count++;
                }
                sum += score[count];
            }
        }
        return sum;
    }
    static class Cor implements Comparable<Cor>{
        int x;
        int y;
        int favoritePeople;
        int empty;

        public Cor(int x, int y, int favoritePeople, int empty) {
            this.x = x;
            this.y = y;
            this.favoritePeople = favoritePeople;
            this.empty = empty;
        }

        @Override
        public int compareTo(Cor o) {
            if(this.favoritePeople == o.favoritePeople) {
                if(this.empty == o.empty) {
                    if(this.x == o.x) {
                        return this.y - o.y;
                    }
                    else return this.x - o.x;
                }
                else return o.empty - this.empty;
            }
            else return o.favoritePeople - this.favoritePeople;
        }
    }
}

