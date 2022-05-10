package AlgorithmStudy.빡구현.P21608;

// 백준 - 상어 초등학교
// 빡구현 문제, 좀 더 간단히 풀어 볼 수 있을듯
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int studentNum;
    static Map<Integer,Integer[]> map=new HashMap<>();
    static int[][] seat;
    static boolean[][] isIn;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        seat=new int[N+2][N+2];
        isIn=new boolean[N+2][N+2];
        studentNum=N*N;
        for(int i=0; i<studentNum; i++){
            st=new StringTokenizer(br.readLine());
            int num=Integer.parseInt(st.nextToken());
            int s1=Integer.parseInt(st.nextToken());
            int s2=Integer.parseInt(st.nextToken());
            int s3=Integer.parseInt(st.nextToken());
            int s4=Integer.parseInt(st.nextToken());
            // 자리 배치 알고리즘
            findSeat(num,new int[]{s1,s2,s3,s4});
            map.put(num,new Integer[]{s1,s2,s3,s4});
        }
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                System.out.print(seat[i][j]+" ");
            }
            System.out.println();
        }

        // 만족도 계산 -> seat 만 구성되면 됨
        int satisfaction=0;
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                // result : 인접한 친구 수
                int nearFriend=0;
                for(int index=0; index<4; index++){
                    int x=i+dy[index];
                    int y=j+dx[index];
                    if(seat[x][y]!=0) nearFriend+=calculate(map.get(seat[i][j]),seat[x][y]);
                }
                if(nearFriend==0) satisfaction+=0;
                else if(nearFriend==1) satisfaction+=1;
                else if(nearFriend==2) satisfaction+=10;
                else if(nearFriend==3) satisfaction+=100;
                else satisfaction+=1000;
            }
        }
        System.out.println(satisfaction);
    }
    public static void findSeat(int num, int[] liked){
        // 1. 좋아하는 친구와 인접한 자리 좌표
        // 2. 빈 자리가 많은 자리 좌표
        // 3. 좌표 정렬 후 첫 번째
        List<Cor> list1=new ArrayList<>();
        List<Cor> list2=new ArrayList<>();
        int[][] numLiked=new int[N+2][N+2];
        int[][] numEmpty=new int[N+2][N+2];
        int locX=-1;
        int locY=-1;
        // 1. 좋아하는 친구와 인접한 수 배열
        for(int index=0; index<4; index++){
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(seat[i][j]==liked[index]) {
                        for(int t=0; t<4; t++){
                            int r=i+dy[t];
                            int c=j+dx[t];
                            if(r!=0&& r!=N+1 && c!=0 && c!=N+1 && seat[r][c]==0) numLiked[r][c]+=1;
                        }
                        break;
                    }
                }
            }
        }
        // 1-1. 인접친구 가장 많은 좌표 찾기
        int maxFriend=0;
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                maxFriend=Math.max(maxFriend,numLiked[i][j]);
            }
        }
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                if(!isIn[i][j] && numLiked[i][j]==maxFriend) list1.add(new Cor(i,j));
            }
        }

        // 2. 빈 자리가 많은 자리 좌표
        for(int i=0; i<list1.size(); i++){
            int x=list1.get(i).getX();
            int y=list1.get(i).getY();
            for(int index=0; index<4; index++){
                int X=x+dy[index];
                int Y=y+dx[index];
                if(X!=0 && X!=N+1 && Y!=0 && Y!=N+1 && seat[X][Y]==0) numEmpty[x][y]+=1;
            }
        }


        // 2-1. 인접 자리 중 빈자리 가장 많은 좌표 구하기
        int maxEmpty=0;
        for(int i=0; i<list1.size(); i++){
            int x=list1.get(i).getX();
            int y=list1.get(i).getY();
            maxEmpty=Math.max(maxEmpty,numEmpty[x][y]);
        }

        for(int i=0; i<list1.size(); i++){
            int x=list1.get(i).getX();
            int y=list1.get(i).getY();
            if(numEmpty[x][y]==maxEmpty) list2.add(new Cor(x,y));
        }


        // 3. 좌표 정렬 후 첫 번째
        Collections.sort(list2);
        locX=list2.get(0).getX();
        locY=list2.get(0).getY();
        seat[locX][locY]=num;
        isIn[locX][locY]=true;

    }

    public static int calculate(Integer[] array, int target){
        for(int i=0; i<4; i++) {
            if(array[i].intValue()==target) return 1;
        }
        return 0;
    }
}
class Cor implements Comparable<Cor>{
    int x,y;
    public Cor(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public int compareTo(Cor o) {
        if(this.x > o.x) return 1;
        else if(this.x == o.x) {
            if(this.y > o.y) return 1;
            else return -1;
        }
        else return -1;
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }
}
