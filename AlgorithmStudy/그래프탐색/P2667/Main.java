package AlgorithmStudy.그래프탐색.P2667;

// 그래프 - DFS, BFS의 활용 문제
// DFS 로 풀어봄
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dy={-1,0,1,0}; // 북 동 남 서
    static int[] dx={0,1,0,-1}; // 북 동 남 서

    static ArrayList<Integer> apt=new ArrayList<>();
    static int apartment;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());

        // 지도 입력받기
        map=new int[N+1][N+1];
        visited=new boolean[N+1][N+1];
        for(int i=1; i<N+1; i++){
            String s=br.readLine();
            for(int j=1; j<N+1; j++){
                map[i][j]=s.charAt(j-1)-'0';
            }
        }

        // map[1][1] ~ map[N][N] 까지 들리면서 연결된 곳 순회하기
        for (int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                apartment=0;
                if(map[i][j]==1 && !visited[i][j]){
                    dfs(i,j);
                    apt.add(apartment);
                }
            }
        }

        // 출력
        sb.append(apt.size()).append("\n");
        Collections.sort(apt);
        for(int n: apt){
            sb.append(n).append("\n");
        }
        System.out.println(sb);

    }
    static void dfs(int y,int x){
        // 1. 체크인
        visited[y][x]=true;
        // 2. 목적지인가?
        apartment++;
        // 3. 연결된 곳 순회
        for(int i=0; i<4; i++) {
            int Y=y+dy[i];
            int X=x+dx[i];
            //      4. 갈 수 있는가?
            if( Y<=N && X<=N && map[Y][X]==1 && !visited[Y][X] ) {
                //          5. 간다
                dfs(Y,X);
            }
        }
        // 6. 체크아웃
    }
}
