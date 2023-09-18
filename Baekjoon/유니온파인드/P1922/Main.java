package Baekjoon.유니온파인드.P1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Kruskal MST 알고리즘
public class Main {
    static int N, M;
    static List<Segment> list = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a != b) {
                list.add(new Segment(a, b, c));
            }
        }

        list.sort((Comparator.comparingInt(o -> o.cost)));

        int sum = 0;
        int count = 0;
        for (Segment s : list) {
            if (getRoot(s.start) != getRoot(s.end)) {
                union(s.start, s.end);
                System.out.println(s.cost);
                sum += s.cost;
                count++;
            }
            if (count == N - 1) {
                break;
            }
        }
        System.out.println(sum);
    }

    public static void union(int x, int y) {
        int rootX = getRoot(x);
        int rootY = getRoot(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static int getRoot(int x) {
        if(parent[x] == x) return x;
        else return parent[x] = getRoot(parent[x]);
    }

    static class Segment{
        int start;
        int end;
        int cost;

        public Segment(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
