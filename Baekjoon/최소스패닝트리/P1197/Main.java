package Baekjoon.최소스패닝트리.P1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[][] graph;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new int[E][3];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }

        // cost 에 따라서 오름차순 정렬
        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);

        // parent 배열 초기화
        parent = new int[V + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 0; i < E; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                union(graph[i][0], graph[i][1]);
                result += graph[i][2];
            }
        }
        System.out.println(result);
    }

    public static int find(int x) {
        if(parent[x]==x) return x;
        else return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // 작은 쪽이 root 가 root 되도록
        if (rootX < rootY) {
            parent[rootY] = rootX;
        } else if (rootX > rootY) {
            parent[rootX] = rootY;
        }
    }
}
