package Baekjoon.그래프탐색.P2667;

// P2667
// DFS 풀이
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int count;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Integer> homes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        // map 돌면서 dfs 실행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count=0;
                if(map[i][j]==1){
                    dfs(i,j);
                    homes.add(count);
                }
            }
        }
        System.out.println(homes);
    }

    public static void dfs(int x, int y) {
        // 1. 체크인
        map[x][y]=0;
        // 2. 목적지인가?
        count++;
        // 3. 연결된 곳 순회
        for (int d = 0; d < 4; d++) {
            int tempX = x + dx[d];
            int tempY = y + dy[d];
            // 4. 갈 수 있는가? -> map 안에 존재 & map[tempX][tempY]=1
            if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < N && map[tempX][tempY] == 1) {
                // 5. 간다
                dfs(tempX,tempY);
            }
        }
    }

}
