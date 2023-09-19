package Baekjoon.위상정렬.P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// dfs 방식을 이용한 위상정렬
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

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
        }

        isVisited = new boolean[N + 1];
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
        isVisited[start] = true;
        for (int v : graph.get(start)) {
            if (!isVisited[v])
                dfs(v);
        }
        stack.push(start);
    }
}
