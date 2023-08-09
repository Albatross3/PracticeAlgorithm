package Baekjoon.그래프탐색.P2667;

// p2667 문제
// bfs 풀이인데 사실상 필요한 것이 좌표이므로
// 큐가 받는 데이터 형을 int[] 로 받아서 진행 가능
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy= {0, 1, 0, -1};
    static List<Integer> homes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]==1 && !isVisited[i][j]){
                    int count=bfs(new int[]{i,j});
                    homes.add(count);
                }
            }
        }
        Collections.sort(homes);
        StringBuilder sb = new StringBuilder();
        sb.append(homes.size()).append("\n");
        for (int c : homes) {
            sb.append(c).append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        isVisited[start[0]][start[1]]=true;

        int count=0;
        while (!q.isEmpty()) {
            // 1. 큐에서 빼기
            int[] p = q.poll();
            // 2. 목적지인가?
            count++;
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = p[0] + dx[d];
                int tempY = p[1] + dy[d];
                // 4. 갈 수 있는가? -> map 안에 존재 & 1 & 방문하지 않은 곳
                if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < N && map[tempX][tempY]==1 && !isVisited[tempX][tempY]) {
                    // 5. 간다
                    isVisited[tempX][tempY]=true;
                    // 6. 큐에 넣기
                    q.add(new int[]{tempX,tempY});
                }
            }
        }
        return count;
    }
}
