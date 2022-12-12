package AlgorithmStudy.위상정렬.P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] isVisited;
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // graph 생성 -> 인접리스트
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        isVisited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end); // DAG
        }

        // 모든 정점에 대하여 dfs 진행
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i])
                dfs(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    public static void dfs(int start) {
        // 1. 체크인
        isVisited[start] = true;
        // 2. 목적지인가?
        // 3. 연결된 곳 순회
        for (int v : graph.get(start)) {
            // 4. 갈 수 있는가?
            if (!isVisited[v])
                // 5. 간다
                dfs(v);
        }
        stack.push(start);
    }
}
