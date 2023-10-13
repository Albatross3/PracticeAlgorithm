package SAMSUNG.SW_2021_상반기.오후.나무_타이쿤;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] map;
    static List<int[]> moves;
    static List<int[]> specialPills;
    static boolean[][] specialPillsPosition;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {1,1,0,-1,-1,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moves = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int p = Integer.parseInt(st.nextToken());
            moves.add(new int[] {d, p});
        }

        // 초기 위치는 우하단에 4개
        specialPills = new ArrayList<>();
        specialPills.add(new int[] {n-2, 0});
        specialPills.add(new int[] {n-2, 1});
        specialPills.add(new int[] {n-1, 0});
        specialPills.add(new int[] {n-1, 1});
        specialPillsPosition = new boolean[n][n];

        for(int i=0; i<m; i++) {
            int[] move = moves.get(i);
            // 1. 특수 영양제 이동
            specialPillMove(move);
            // 2. 특수 영양제 투입 -> 해당 위치 기록
            insertPill();
            // 3. 투입된 위치의 리브로수 성장
            treeGrow();
            // 4. 새로운 특수 영양제 위치 생김
            createNewSpecialPill();
        }
        System.out.println(printScore());
    }

    static void specialPillMove(int[] move) {
        int d = move[0]; // 방향
        int l = move[1]; // 이동 길이
        for(int[] specialPill : specialPills) {
            specialPill[0] = (specialPill[0]+l*dx[d]+n) % n;
            specialPill[1] = (specialPill[1]+l*dy[d]+n) % n;
        }
    }

    static void insertPill() {
        for(int[] specialPill : specialPills) {
            int x = specialPill[0];
            int y = specialPill[1];
            specialPillsPosition[x][y] = true;
            map[x][y]++;
        }
        // 특수 영양제 list 초기화
        specialPills = new ArrayList<>();
    }

    static void treeGrow() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n ;j++) {
                if(specialPillsPosition[i][j]) {
                    int count=0;
                    for(int d=1; d<8; d+=2) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                        if(map[nx][ny]>=1) count++;
                    }
                    map[i][j] += count;
                }
            }
        }
    }

    static void createNewSpecialPill() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n ;j++) {
                if(!specialPillsPosition[i][j] && map[i][j]>=2) {
                    map[i][j]-= 2;
                    specialPills.add(new int[] {i,j});
                }
                specialPillsPosition[i][j] = false;
            }
        }
    }

    static int printScore() {
        int score = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n ;j++) {
                score += map[i][j];
            }
        }
        return score;
    }
}
