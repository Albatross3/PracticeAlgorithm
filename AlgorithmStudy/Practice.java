package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Practice {
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static int N,M;
    static int[][] map;
    static int[][] dp;
    static boolean[][] isVisited;
    static Queue<Point> queue=new LinkedList<>();
    static int result=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        dp=new int[N][M];
        isVisited=new boolean[N][M];
        for(int i=0; i<N; i++){
            String s=br.readLine();
            for(int j=0; j<M; j++){
                map[i][j]=s.charAt(j)-'0';
            }
        }
        dp[0][0]=1;
        queue.add(new Point(0,0));
        bfs();
        System.out.println(result);
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void bfs(){

        while(!queue.isEmpty()){
            // 1. 큐에서 꺼내기
            Point p=queue.poll();
            // 2. 목적지인가?
            if(p.getI()==N-1 && p.getJ()==M-1){
                result=dp[N-1][M-1];
                return;
            }
            // 3. 연결된 곳 순회
            for(int d=0; d<4; d++){
                int tempX=p.getI()+dy[d];
                int tempY=p.getJ()+dx[d];
                // 4. 갈 수 있는가? -> 맵 안에 존재 & 1 & 방문하지 않은 곳
                if(tempX>=0 &&tempX<N && tempY>=0 && tempY<M && map[tempX][tempY]==1 && !isVisited[tempX][tempY]){
                    // 5. 간다
                    isVisited[tempX][tempY]=true;
                    dp[tempX][tempY]=dp[p.getI()][p.getJ()]+1;
                    // 6. 큐에 넣기
                    queue.add(new Point(tempX,tempY));
                }
            }
        
        }

        

        //          6. 큐에 넣기
    }
}
class Point{
    int i,j;
    int value;
    public Point(int i,int j){
        this.i=i;
        this.j=j;
    }
    public int getI(){return i;}
    public int getJ(){return j;}
}