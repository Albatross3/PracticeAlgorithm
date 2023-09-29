package Baekjoon.그래프탐색.깊이우선탐색.P11266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static List<List<Integer>> graph = new ArrayList<>();
    static int order = 1;
    static boolean[] isArticulationPoint;
    static int[] visitedOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        isArticulationPoint = new boolean[V + 1];
        visitedOrder = new int[V + 1];

        for (int i = 1; i < V + 1; i++) {
            if (visitedOrder[i] == 0) {
                dfs(i, true);
            }
        }

        int count=0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < isArticulationPoint.length; i++) {
            if (isArticulationPoint[i]) {
                count++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }

    public static int dfs(int current, boolean isStart) {
        visitedOrder[current] = order++;
        int ret = visitedOrder[current];
        int child = 0;

        for (int next : graph.get(current)) {
            if (visitedOrder[next] == 0) {
                child++;
                int low = dfs(next, false);
                if (!isStart && visitedOrder[current] <= low) {
                    isArticulationPoint[current] = true;
                }
                ret = Math.min(ret, low);
            } else {
                ret = Math.min(ret, visitedOrder[next]);
            }
        }

        if (isStart && child >= 2) {
            isArticulationPoint[current] = true;
        }

        return ret;
    }
}
