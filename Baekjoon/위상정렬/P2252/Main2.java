package Baekjoon.위상정렬.P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 진입차수를 이용한 bfs 방식의 위상정렬
public class Main2 {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        inDegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            inDegree[e]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);
            for (int v : graph.get(cur)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int x : result) {
            sb.append(x).append(" ");
        }
        System.out.println(sb);
    }
}
