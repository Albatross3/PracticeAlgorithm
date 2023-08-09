package Baekjoon.시뮬레이션.P21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 12:00~2:06
public class Main {
    static int[] dy={0,-1,-1,-1,0,1,1,1};
    static int[] dx={-1,-1,0,1,1,1,0,-1};
    static int N,M;
    static int[][] map;
    static int[] D;
    static int[] S;
    static boolean[][] visited;
    static Queue<Point> queue=new LinkedList<>();
    static Queue<Point> queue2=new LinkedList<>();
    static Queue<Point> queue3=new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N+2][N+2];
        visited=new boolean[N+2][N+2];
        for(int i=1; i<N+1; i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        D=new int[M];
        S=new int[M];
        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            D[i]=Integer.parseInt(st.nextToken());
            S[i]=Integer.parseInt(st.nextToken());
        }

        // 시뮬레이션
        queue.add(new Point(N,1,map[N][1]));
        queue.add(new Point(N,2,map[N][2]));
        queue.add(new Point(N-1,1,map[N-1][1]));
        queue.add(new Point(N-1,2,map[N-1][2]));
        int x,y,tempX,tempY,newX,newY;
        Point p;
        for(int c=0; c<M; c++){
            int d=D[c];
            int s=S[c];

            // 1. 모든 구름 d 방향으로 s 만큼 이동
            // 2. 저장된 바구니 물 1 증가
            // 3. 구름 소멸

            while(!queue.isEmpty()){
                p=queue.poll();
                x=p.getX();
                y=p.getY();
                tempX=x+dy[d-1]*s;
                tempY=y+dx[d-1]*s;
                if(tempX>=0) {
                    if(tempX%N==0) newX=N;
                    else newX=tempX%N;
                }else{
                    if(tempX%N==0) newX=N;
                    else newX=N+tempX%N;
                }
                if(tempY>=0) {
                    if(tempY%N==0) newY=N;
                    else newY=tempY%N;
                }else{
                    if(tempY%N==0) newY=N;
                    else newY=N+tempY%N;
                }
                map[newX][newY]+=1;
                queue2.add(new Point(newX,newY,map[newX][newY]));
                queue3.add(new Point(newX,newY,map[newX][newY]));
            }

            // 4. 물복사 버그 마법
            while(!queue2.isEmpty()){

                Point p2=queue2.poll();
                x=p2.getX();
                y=p2.getY();
                for(int i=1; i<8; i+=2){
                    tempX=x+dy[i];
                    tempY=y+dx[i];
                    if(tempX>=1 && tempX<=N && tempY>=1 && tempY<=N && map[tempX][tempY]!=0) map[x][y]+=1;
                }
            }
            // 5. 새로운 구름 생성
            while (!queue3.isEmpty()){
                Point p3=queue3.poll();
                x=p3.getX();
                y=p3.getY();
                visited[x][y]=true;
            }

            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(map[i][j]>=2 && !visited[i][j]) {
                        map[i][j]-=2;
                        queue.add(new Point(i, j, map[i][j]));
                    }
                }
            }
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(visited[i][j]) visited[i][j]=false;
                }
            }
        }

        int sum=0;
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                sum+=map[i][j];
            }
        }
        System.out.println(sum);
    }
}
class Point{
    int x,y;
    int water;
    public Point(int x,int y, int water){
        this.x=x;
        this.y=y;
        this.water=water;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
