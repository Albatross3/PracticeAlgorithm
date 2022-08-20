package AlgorithmStudy.그래프탐색.P3055;

// 백준 - 탈출
// 대표적인 bfs 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static int R,C;
    static char[][] map;
    static int[][]  dp;
    static Queue<Position2> queue=new LinkedList<>();
    static boolean isExist;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        map=new char[R][C];
        dp=new int[R][C];
        // queue 초기화 ( S,* 위치에 따라 달라지는지 확인 )

        Position2 P=null;
        for(int i=0; i<R; i++){
            String s=br.readLine();
            for(int j=0; j<C; j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='S') P=new Position2(i,j,'S');
                if(map[i][j]=='*') queue.add(new Position2(i,j,'*'));
            }
        }
        queue.add(P);


        //  bfs 시작
        while(!queue.isEmpty()){
            // 1. 큐에서 꺼내기
            Position2 p= queue.poll();
            // 2. 목적지 인가?
            if(p.getType()=='D'){
                System.out.println(dp[p.getI()][p.getJ()]);
                isExist=true;
                break;
            }
            // 3. 연결된 곳을 순회
            for(int i=0; i<4; i++){
                int newX=p.getI()+dy[i];
                int newY=p.getJ()+dx[i];

                //      4. 갈 수 있는가?
                if(newX>=0 && newX<R && newY>=0 && newY<C){
                    if(p.getType()=='.' || p.getType()=='S'){
                        if((map[newX][newY]=='.'|| map[newX][newY]=='D') && dp[newX][newY]==0){
                            //          5. 간다
                            dp[newX][newY]=dp[p.getI()][p.getJ()]+1;
                            //          6. 큐에 넣기
                            queue.add(new Position2(newX,newY,map[newX][newY]));
                        }
                    }

                    if(p.getType()=='*'){
                        if(map[newX][newY]=='.'|| map[newX][newY]=='S'){
                            //          5. 간다
                            map[newX][newY]='*';
                            //          6. 큐에 넣기
                            queue.add(new Position2(newX,newY,'*'));
                        }
                    }
                }
            }
        }
        if(!isExist) System.out.println("KAKTUS");
    }
}
class Position2 {
    int i,j;
    char type;
    public Position2(int i, int j, char type){
        this.i=i;
        this.j=j;
        this.type=type;
    }

    public char getType() {
        return type;
    }
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
}
