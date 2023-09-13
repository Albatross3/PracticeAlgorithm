package Baekjoon.재귀.P1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // dp 가 파스칼 삼각형임
        dp = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < M + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                dp[i][j] = Math.min((int)1e9, dp[i][j - 1] + dp[i - 1][j]);
            }
        }
        if (dp[N][M] < K) {
            System.out.println(-1);
        } else {
            System.out.println(query(N, M, K, ""));
        }

    }

    public static String query(int n, int m, int order, String answer) {
        if (n == 0 || m == 0) {
            if (n == 0) {
                return answer + "z".repeat(m);
            } else {
                return answer + "a".repeat(n);
            }
        }

        if (dp[n - 1][m] >= order) {
            return query(n - 1, m, order, answer + "a");
        } else {
            return query(n, m - 1, order - dp[n - 1][m], answer + "z");
        }
    }
}
