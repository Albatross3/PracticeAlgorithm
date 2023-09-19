package Baekjoon.최단거리.벨만포드.P11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge> list = new ArrayList<>();
    static long[] result;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Edge(s, e, c));
        }

        result = new long[N + 1];
        Arrays.fill(result, INF);
        result[1] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = list.get(j);
                if (result[edge.start] != INF && result[edge.end] > result[edge.start] + edge.cost) {
                    result[edge.end]  = result[edge.start] + edge.cost;
                }
            }
        }

        // 음수 사이클 확인
        boolean isNegativeCycle = false;
        for (int i = 0; i < M; i++) {
            Edge edge = list.get(i);
            if (result[edge.start] != INF && result[edge.end] > result[edge.start] + edge.cost) {
                isNegativeCycle = true;
                break;
            }
        }

        if (isNegativeCycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i < result.length; i++) {
                if(result[i] == INF) System.out.println(-1);
                else System.out.println(result[i]);
            }
        }
    }

    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
