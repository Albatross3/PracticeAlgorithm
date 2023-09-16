package Baekjoon.조합론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 순열 {
    static int N, M;
    static boolean[] isVisited;
    static int[] permutation;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isVisited = new boolean[N + 1];
        permutation = new int[M];
        getPermutation(0);
        System.out.println(sb);
    }
    public static void getPermutation(int depth) {
        if (depth == M) {
            for (int i = 0; i < permutation.length; i++) {
                sb.append(permutation[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                permutation[depth] = i;
                getPermutation(depth + 1);
                isVisited[i] = false;
            }
        }
    }
}
