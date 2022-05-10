package AlgorithmStudy.그래프탐색.P1012;

// 그래프 - DFS, BFS 활용문제
// BFS 로 구현해 봄
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int M,N,K;

    static int[][] map;
    static boolean[][] visited;

    static int[] dy={-1,0,1,0}; // 북 동 남 서
    static int[] dx={0,1,0,-1}; // 북 동 남 서

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st;
        T=Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++){
            int count=0;

            st=new StringTokenizer(br.readLine());
            M=Integer.parseInt(st.nextToken());
            N=Integer.parseInt(st.nextToken());
            K=Integer.parseInt(st.nextToken());

            map=new int[N][M];
            visited=new boolean[N][M];
            for(int k=0; k<K; k++){
                st=new StringTokenizer(br.readLine());
                int x=Integer.parseInt(st.nextToken());
                int y=Integer.parseInt(st.nextToken());
                map[y][x]=1;
            }

            // map[0][0] ~ map[N][M] 까지 bfs 로 연결된 곳 찾기
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j]==1 && !visited[i][j]){
                        bfs(i,j);
                        count++;
                    }
                }
            }

            // 출력
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
    static void bfs(int y,int x) {
        // 0. 시작
        Queue<Cor> queue=new LinkedList<>();
        queue.add(new Cor(y,x));
        visited[y][x]=true;

        while(queue.size()!=0) {
            // 1. 큐에서 꺼내기
            Cor temp = queue.poll();
            // 2. 목적지인가?
            // 3. 연결된 곳 순회
            for (int i = 0; i < 4; i++) {
                int Y = temp.y + dy[i];
                int X = temp.x + dx[i];
                //      4. 갈 수 있는가? -> map 안에 존재하고 방문하지 않았으며 배추가 있는 곳
                if (X >= 0 && X < M && Y >= 0 && Y < N) {
                    if (map[Y][X] == 1 && !visited[Y][X]) {
                        //          5. 체크인
                        visited[Y][X] = true;
                        //          6. 큐에 넣는다
                        queue.add(new Cor(Y, X));
                    }
                }
            }
        }
    }
}
class Cor{
    int x,y;
    public Cor(int y,int x){
        this.x=x;
        this.y=y;
    }

}