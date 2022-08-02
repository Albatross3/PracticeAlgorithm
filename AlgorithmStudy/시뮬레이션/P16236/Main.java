package AlgorithmStudy.시뮬레이션.P16236;
// 아기 상어
// 최단 경로를 구하는 문제는 bfs 사용, 
// tree 구조라고 확신 하는 경우만 dfs 사용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] dp;
    static int size=2;
    static int time=0;
    static PriorityQueue<Position> pq=new PriorityQueue<>((o1, o2) -> {
        if(o1.distance < o2.distance) return -1;
        else if (o1.distance == o2.distance) {
            if(o1.x < o2.x) return -1;
            else if (o1.x == o2.x) return Integer.compare(o1.y,o2.y);
            else return 1;
        }
        else return 1;
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
                if (value == 9) {
                    pq.add(new Position(i,j,0));
                }
                map[i][j] = value;
            }
        }
        // 현재 위치로부터 탐색해서 먹을 수 있는 물고기 우선순위 큐에 넣기
        int eatNum=0;
        while (!pq.isEmpty() ) {
            // 1. 큐에서 꺼내기
            Position p=pq.poll();
            dp=new int[N][N];

            // 탐색
            bfs(p.x, p.y);
            if(pq.isEmpty()) break;
            else{
                Position destination=pq.poll();
                time+= destination.distance;
                pq.clear();
                // 이동 + 변화
                pq.add(destination);
                map[destination.x][destination.y]=9;
                map[p.x][p.y]=0;
                eatNum++;
                if(eatNum==size){
                    size++;
                    eatNum=0;
                }
            }
        }
        System.out.println(time);
    }
    public static void bfs(int curX,int curY) {
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(curX, curY, 0));
        while (!q.isEmpty()) {
            // 1. 큐에서 꺼낸다
            Position p = q.poll();
            // 2. 목적지인가?
            if(map[p.x][p.y]<size && map[p.x][p.y]!=0) pq.add(p);
            // 3. 연결된 곳 순회
            for (int i = 0; i < 4; i++) {
                int newX = p.x + dy[i];
                int newY = p.y + dx[i];
                // 4. 갈 수 있는가?
                if ((newX >= 0 && newX < N) && (newY >= 0 && newY < N) && dp[newX][newY]==0) {
                    //  5. 간다
                        if(map[newX][newY]<=size) {
                            dp[newX][newY]=dp[p.x][p.y]+1;
                            // 6. 큐에 넣기
                            q.add(new Position(newX, newY, dp[newX][newY]));
                    }
                }
            }

        }
    }
}
class Position  {
    int x,y;
    int distance;

    public Position(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}