package SAMSUNG.SW_2022_상반기.오후.나무박멸;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k,c;
    static int[][] map;
    static int[][] treeKiller;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[] lx = {-1,1,1,-1};
    static int[] ly = {1,1,-1,-1};
    static int removedTotal = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        treeKiller = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(m-->0) {
            // 제초제 남은 년수 감소
            decreaseRemainYear();
            // 나무 성장
            growTree();
            // 나무 번식
            expandTree();
            // 제초제 위치 선정
            int[] killerPosition = findKillerPosition();
            // 제초제 뿌리기
            killTree(killerPosition);
        }
        System.out.println(removedTotal);
    }

    static void decreaseRemainYear() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(treeKiller[i][j]!=0) {
                    treeKiller[i][j]--;
                }
            }
        }
    }

    static void growTree() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j]>0) {
                    int trees = 0;
                    for(int d=0; d<4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                        if(map[nx][ny]>0) trees++;
                    }
                    map[i][j] += trees;
                }
            }
        }
    }

    static void expandTree() {
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j]>0) {
                    int empty = 0;
                    for(int d=0; d<4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                        if(treeKiller[nx][ny]==0 && map[nx][ny]==0) {
                            empty++;
                        }
                    }
                    for(int d=0; d<4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                        if(treeKiller[nx][ny]==0 && map[nx][ny]==0) {
                            temp[nx][ny] += map[i][j]/empty;
                        }
                    }
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(temp[i][j]!=0) {
                    map[i][j] = temp[i][j];
                }
            }
        }
    }

    static int[] findKillerPosition() {
        int max = 0;
        int X = 0;
        int Y = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j]>0) {
                    int removed = removedAmount(i,j);
                    if(removed > max) {
                        max = removed;
                        X = i;
                        Y = j;
                    }
                }
            }
        }
        return new int[]{X,Y};
    }

    static int removedAmount(int x, int y) {
        int amount = map[x][y];
        for(int l=0; l<4; l++) {
            for(int r=1; r<=k; r++) {
                int nx = x + r*lx[l];
                int ny = y + r*ly[l];
                if(nx<0 || nx>=n || ny<0 || ny>=n) break;
                if(map[nx][ny]==-1 || map[nx][ny]==0) break;
                amount += map[nx][ny];
            }
        }
        return amount;
    }

    static void killTree(int[] start) {
        if(map[start[0]][start[1]]==-1) return;
        treeKiller[start[0]][start[1]] = c+1;
        removedTotal += map[start[0]][start[1]];
        map[start[0]][start[1]] = 0;
        for(int l=0; l<4; l++) {
            for(int r=1; r<=k; r++) {
                int nx = start[0] + r*lx[l];
                int ny = start[1] + r*ly[l];
                if(nx<0 || nx>=n || ny<0 || ny>=n) break;
                // 나무인 경우
                if(map[nx][ny] > 0) {
                    treeKiller[nx][ny] = c+1;
                    removedTotal += map[nx][ny];
                    map[nx][ny] = 0;
                }
                // 벽이나 빈 칸인 경우
                else {
                    treeKiller[nx][ny] = c+1;
                    break;
                }
            }
        }
    }

}
