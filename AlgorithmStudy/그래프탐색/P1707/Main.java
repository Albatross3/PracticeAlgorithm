package AlgorithmStudy.그래프탐색.P1707;
// 백준 - 이분 그래프
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int K;
    static int V, E;

    static ArrayList<ArrayList<Integer>> graph;
    static int[] color;
    static boolean[] isVisited;

    static boolean isBipartiteGraph;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        for (int t = 0; t < K; t++) {
            isBipartiteGraph=true;
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            // 그래프 생성
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }
            // 그래프 입력
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            // 각 정점마다의 색
            color = new int[V + 1];
            // bfs -> 같은 level 같은 색으로 칠하기
            // 시작점을 모르니 vertex 마다 bfs 진행
            isVisited = new boolean[V + 1];

            for (int i = 1; i <= V; i++) {
                if(!isVisited[i])
                    bfs(i);
            }

            // 인접해있을 때 다른 색인지 확인
            for (int i = 1; i <= V; i++) {
                for (int vertex : graph.get(i)) {
                    if(color[i]==color[vertex]) {
                        isBipartiteGraph = false;
                        break;
                    }
                }
            }

            if(isBipartiteGraph) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");

        }
        System.out.println(sb);
    }

    public static void bfs(int start) {
        isVisited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start]=1;

        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            int vertex = q.poll();
            // 2. 목적지인가? -> x
            // 3. 연결된 곳 순회
            for (int v : graph.get(vertex)) {
                // 4. 갈 수 있는가?
                if (!isVisited[v]) {
                    // 색 갱신
                    color[v] = color[vertex] * -1;
                    // 5. 간다
                    isVisited[v]=true;
                    // 6. 큐에 넣기
                    q.add(v);
                }
            }
        }


    }
}
