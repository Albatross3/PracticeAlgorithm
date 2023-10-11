package SAMSUNG.SW_2021_상반기.오전.색깔_폭탄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    static boolean[][] isVisited;
    static List<BombSet> bombSetList;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int total = 0;
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
        while(findMaxSizeBombSet()) {
            // 1. 폭탄 제거 및 점수 증가
            List<int[]> corList = bombSetList.get(0).bombSet;
            for(int[] cor : bombSetList.get(0).bombSet) {
                map[cor[0]][cor[1]] = -2; // 빈칸으로 만들기
            }
            total += corList.size()*corList.size();

            // 2. 중력 작용
            applyGravity();
            // 3. 반시계 90도 회전
            rotateCounterClockWise90();
            // 4. 중력 작용
            applyGravity();
        }
        System.out.println(total);
    }

    static boolean findMaxSizeBombSet() {
        isVisited = new boolean[n][n]; // 전체 총괄 색깔 폭탄들만 칠해
        bombSetList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                // 1~m 이고 방문하지 않은 경우만 탐색 가능
                if(!isVisited[i][j] && map[i][j]>=1) {
                    findBombSet(new int[] {i,j}, map[i][j]);
                }
            }
        }
        Collections.sort(bombSetList);
        return !bombSetList.isEmpty();
    }

    static void findBombSet(int[] start, int color) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        boolean[][] eachIsVisited = new boolean[n][n];
        isVisited[start[0]][start[1]] = true;
        eachIsVisited[start[0]][start[1]] = true;

        int numRed = 0;
        List<int[]> bombSet = new ArrayList<>();
        List<int[]> notRedBombSet = new ArrayList<>();
        bombSet.add(start);
        notRedBombSet.add(start);
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx<0 || nx>=n || ny<0 ||ny>=n) continue;
                if(eachIsVisited[nx][ny]) continue;
                // 빨간색인 경우
                if(map[nx][ny] == 0) {
                    numRed++;
                    eachIsVisited[nx][ny] = true;
                    int[] next = new int[] {nx,ny};
                    q.add(next);
                    bombSet.add(next);
                }
                // 시작 색과 같은 경우
                else if(map[nx][ny] == color) {
                    isVisited[nx][ny] = true;
                    eachIsVisited[nx][ny] = true;
                    int[] next = new int[] {nx,ny};
                    q.add(next);
                    bombSet.add(next);
                    notRedBombSet.add(next);
                }

            }
        }

        // 올바른 폭탄 묶음인 경우
        if(bombSet.size() >= 2) {
            Collections.sort(notRedBombSet, (o1, o2) -> {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                else return o2[0] - o1[0];
            });
            int[] base = notRedBombSet.get(0);
            BombSet set = new BombSet(bombSet.size(), numRed, base[0], base[1], bombSet);

            bombSetList.add(set);
        }

    }

    static void applyGravity() {
        for(int j=0; j<n; j++) {
            for(int i=n-2; i>=0; i--) {
                if(map[i][j]==-1 || map[i][j]==-2) continue;
                int startX = i+1;
                while(map[startX][j]==-2) {
                    startX++;
                    if(startX == n) break;
                }
                if(startX-1!=i) {
                    map[startX-1][j] = map[i][j];
                    map[i][j] = -2;
                }

            }
        }
    }

    static void rotateCounterClockWise90() {
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                temp[n-1-j][i] = map[i][j];
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    static class BombSet implements Comparable<BombSet>{
        int size;
        int numRed;
        int baseX;
        int baseY;
        List<int[]> bombSet;

        public BombSet(int size, int numRed, int baseX, int baseY, List<int[]> bombSet) {
            this.size = size;
            this.numRed = numRed;
            this.baseX = baseX;
            this.baseY = baseY;
            this.bombSet = bombSet;
        }

        @Override
        public int compareTo(BombSet o) {
            if(this.size == o.size) {
                if(this.numRed == o.numRed) {
                    if(this.baseX == o.baseX) {
                        return this.baseY - o.baseY;
                    }
                    else return o.baseX - this.baseX;
                }
                else return this.numRed - o.numRed;
            }
            else return o.size - this.size;
        }
    }
}
