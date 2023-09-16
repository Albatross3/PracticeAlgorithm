package Baekjoon.조합론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 조합 {
    static int N,M;
    static boolean[] isVisited;
    static int[] combination;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N + 1];
        combination = new int[M + 1];
        getCombination(0);
        System.out.println(sb);
    }

    public static void getCombination(int depth) {
        if (depth == M) {
            for (int i = 1; i < combination.length; i++) {
                sb.append(combination[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i] && combination[depth] < i) {
                isVisited[i] = true;
                combination[depth + 1] = i;
                getCombination(depth + 1);
                isVisited[i] = false;
            }
        }
    }
}
