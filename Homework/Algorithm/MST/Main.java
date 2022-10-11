package Homework.Algorithm.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Edge[] edgeSet;
    static ArrayList<Edge> MST = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 간선 배열 생성
        edgeSet = new Edge[M];
        // 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeSet[i] = new Edge(x, y, w);
        }
        // 간선 배열 정렬
        Arrays.sort(edgeSet);

        // Union - find 를 위한 tree 생성
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        // 크러스컬 알고리즘
        int weightSum = 0;
        for (int i = 0; i < M; i++) {
            int u = edgeSet[i].vertex1;
            int v = edgeSet[i].vertex2;
            if (find(u) != find(v)) {
                MST.add(edgeSet[i]);
                union(u, v);
                weightSum += edgeSet[i].weight;
            }
            // 종료 조건 -> MST 의 수가 N-1이 될 때
            if (MST.size() == N - 1) {
                break;
            }
        }
        System.out.println(weightSum);
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        else return find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        // 부모를 작은 쪽으로 union
        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }
}
class Edge implements Comparable<Edge>{
    int vertex1;
    int vertex2;
    int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight-o.weight;
    }
}