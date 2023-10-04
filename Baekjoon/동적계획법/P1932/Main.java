package Baekjoon.동적계획법.P1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동적계획법 기본 문제
public class Main {
    static int n;
    static int[][] num;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        num = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + num[i][j];
            }
        }

        int max = 0;
        for (int j = 1; j < n + 1; j++) {
            max = Math.max(max, dp[n][j]);
        }
        System.out.println(max);
    }
}
