package AlgorithmStudy;
// 아기 상어
// 최단 경로를 구하는 문제는 bfs 사용, 
// tree 구조라고 확신 하는 경우만 dfs 사용
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int currentX,currentY;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] isVisited;
    static int[][] dp;
    static int size=2;
    static int time=0;
    static PriorityQueue<Position> pq=new PriorityQueue<>(new Comparator<Position>() {
        @Override
        public int compare(Position o1, Position o2) {
            if(o1.distance < o2.distance) return -1;
            else if (o1.distance == o2.distance) {
                if(o1.x < o2.x) return -1;
                else if (o1.x == o2.x) return Integer.compare(o1.y,o2.y);
                else return 1;
            }
            else return 1;
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map=new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value=Integer.parseInt(st.nextToken());
                if(value==9) {
                    currentX=i;
                    currentY=j;
                }
                map[i][j] = value;
            }
        }
        // 현재 위치로부터 탐색해서 우선순위 큐에 넣기
        isVisited=new boolean[N][N];
        dp=new int[N][N];
        isVisited[currentX][currentY]=true;
        find(map,currentX,currentY,size);
        // 이동하고 탐색 -> 반복
        int eatenFish=0;
        while(!pq.isEmpty()){
            // 이동 후 물고기 먹음
            map[currentX][currentY]=0;
            Position p=pq.poll();
            time+=p.distance;
            currentX=p.x; currentY=p.y;
            map[p.x][p.y]=0;
            eatenFish++;
            if(size==eatenFish){
                eatenFish=0;
                size++;
            }
            // 탐색
            // 우선순위 큐 비우기
            pq.clear();
            isVisited=new boolean[N][N];
            dp=new int[N][N];
            isVisited[currentX][currentY]=true;
            find(map,currentX,currentY,size);
        }
        System.out.println(time);
    }
    public static void find(int[][] map, int curX,int curY,int curSize){

        for (int i = 0; i < 4; i++) {
            int newX = curX + dx[i];
            int newY = curY + dy[i];
            // 갈 수 있는가? (경계값 + 방문 여부 + 자신의 크기 보다 작거나 같은지 )
            if((newX>=0 && newX<N) && (newY>=0 && newY<N)){
                if(!isVisited[newX][newY] && map[newX][newY]<=curSize){
                    dp[newX][newY]=dp[curX][curY]+1;
                    isVisited[newX][newY]=true;
                    if(map[newX][newY]<curSize && map[newX][newY]>0) {
                        pq.add(new Position(newX, newY, dp[newX][newY]));
                    }
                    find(map, newX, newY, curSize);
                }
            }
        }
    }
}
class Position  {
    int x,y;
    int distance;

    public Position(int x, int y, int distance) {
        this.x=x;
        this.y=y;
        this.distance = distance;
    }
}