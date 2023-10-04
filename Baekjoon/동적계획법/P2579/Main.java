package Baekjoon.동적계획법.P2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 생각하기 조금 어려운 동적계획법 문제
public class Main {
    static int N;
    static int[] stair;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stair = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N + 1][3];
        dp[1][1] = stair[1];
        dp[1][2] = stair[1];

        for (int i = 2; i < N + 1; i++) {
            dp[i][1] = dp[i - 1][2] + stair[i];
            dp[i][2] = Math.max(dp[i-2][1], dp[i-2][2]) + stair[i];
        }

        System.out.println(Math.max(dp[N][1], dp[N][2]));
    }
}
