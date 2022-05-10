package AlgorithmStudy.그래프.P13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static int N,M;
    static char[][] map;
    static int[][] dp;
    static char[][] locA;
    static boolean[][] isVisited;
    static Queue<Point> queue=new LinkedList<>();
    static Queue<Point> queue2=new LinkedList<>();
    static boolean isAin=false;
    static boolean isBIn=false;
    static int result=-1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        locA= new char[N][M];
        dp= new int[N][M];
        isVisited=new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);
                if(map[i][j]=='R') {
                    isVisited[i][j]=true;
                    queue.add(new Point(i,j,'R'));
                }
                else if(map[i][j]=='B') queue2.add(new Point(i,j,'B'));
            }
        }

        // bfs
        while(!queue.isEmpty()){
            // 1. 큐에서 꺼내기
            Point p=queue.poll();
            Point q=queue2.poll();
            locA[p.i][p.j]='.';
            // 2. 목적지인가?
            if(p.type=='O') {
                isAin=true;
                result=dp[p.i][p.j];
                break;
            }
            // 3. 연결된 곳 순회
            for(int d=0; d<4; d++){
                int s=1;
                int tempX=p.i+dy[d];
                int tempY=p.j+dx[d];

                int s2=1;
                int tempX2=q.i+dy[d];
                int tempY2=q.j+dx[d];

                //      4. R 이 갈 수 있는가? ('#' 또는 'B' 또는 왔던 길)
                if(map[tempX][tempY]!='#' && map[tempX][tempY]!='B' && !isVisited[tempX][tempY]) {
                    // R 의 다음 위치 '#'이면 탈출
                    while (map[tempX][tempY] != '#') {
                        //          5. 간다
                        isVisited[tempX][tempY]=true;
                        // 다음 위치 'O' 인 경우
                        if(map[tempX][tempY]=='O'){
                            locA[p.i][p.j]='.';
                            dp[tempX][tempY]=dp[p.i][p.j]+1;
                            queue.add(new Point(tempX,tempY,'O'));
                            break;
                        }
                        s++;
                        tempX=p.i+s*dy[d];
                        tempY=p.j+s*dx[d];
                    }
                    if(map[tempX][tempY]=='#') {
                        locA[p.i + (s - 1) * dy[d]][p.j + (s - 1) * dx[d]]='R';
                        dp[p.i + (s - 1) * dy[d]][p.j + (s - 1) * dx[d]] = dp[p.i][p.j] + 1;
                        queue.add(new Point(p.i + (s - 1) * dy[d], p.j + (s - 1) * dx[d], '.'));
                    }
                   // B의 다음 위치 ('#' 과 'R' 이 아닌 경우 끝까지 간다)
                    while(map[tempX2][tempY2]!='#' && locA[tempX2][tempY2]!='R'){
                        if(map[tempX2][tempY2]=='O'){
                            isBIn=true;
                            break;
                        }
                        s2++;
                        tempX2=q.i+s2*dy[d];
                        tempY2=q.j+s2*dx[d];
                    }
                    if(map[tempX2][tempY2]=='#' || locA[tempX2][tempY2]=='R' ){
                        queue2.add(new Point(q.i+(s2-1)*dy[d],q.j+(s2-1)*dx[d],'.'));
                    }

                }
                if(isBIn) break;
            }



        }
        System.out.println(isBIn);
        if(isAin && !isBIn ) {
            if(result<=10) System.out.println(result);
            else System.out.println(-1);
        }else{
            System.out.println(-1);
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
class Point{
    int i,j;
    char type;
    public Point(int i,int j,char type){
        this.i = i;
        this.j = j;
        this.type = type;
    }
}
