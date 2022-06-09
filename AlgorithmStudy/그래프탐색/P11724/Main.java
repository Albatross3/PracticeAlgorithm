package AlgorithmStudy.그래프탐색.P11724;

// 백준 - 연결 요소의 개수
// 1. dfs 풀이
// 2. Union and Find 풀이
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    static boolean[] isVisited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // graph 인접 리스트 생성 및 표현
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        // 초기화
        isVisited = new boolean[V + 1];

        // dfs 돌면서 dfs 끝나면 1 추가
        for (int i = 1; i <= V; i++) {
            if (!isVisited[i]) {
                // 정점에 연결된 간선 있을 때
                dfs(i);
                count++;
            }
        }
        System.out.println(count);
    }

    public static void dfs(int startVertex) {
        // 1. 체크인
        isVisited[startVertex] = true;
        // 2. 목적지인가?
        // 3. 연결된 곳 순회
        for (int v : graph.get(startVertex)) {
            // 4. 갈 수 있는가?
            if (!isVisited[v]) {
                // 5. 간다
                dfs(v);
            }
        }
        // 6. 체크아웃
    }
}
