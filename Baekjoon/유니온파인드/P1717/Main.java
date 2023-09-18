package Baekjoon.유니온파인드.P1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operation == 0) {
                union(a, b);
            } else if (operation == 1) {
                if (getRoot(a) == getRoot(b)) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    public static void union(int x, int y) {
        int rootX = getRoot(x);
        int rootY = getRoot(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static int getRoot(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = getRoot(parent[x]);
    }
}
