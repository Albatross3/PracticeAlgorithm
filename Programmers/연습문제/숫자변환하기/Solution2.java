package Programmers.연습문제.숫자변환하기;

import java.util.Arrays;

// dp 로 풀이하기
public class Solution2 {
    static int[] dp;
    static int MAX = 1000001;
    public int solution(int x, int y, int n) {
        dp = new int[MAX];
        Arrays.fill(dp, MAX);
        dp[x] = 0;
        for(int i=x; i<dp.length; i++) {
            if(dp[i] != MAX) {
                if(i+n < MAX) dp[i+n] = Math.min(dp[i]+1, dp[i+n]);
                if(i*2 < MAX) dp[i*2] = Math.min(dp[i]+1, dp[i*2]);
                if(i*3 < MAX) dp[i*3] = Math.min(dp[i]+1, dp[i*3]);
            }
        }
        if(dp[y] == MAX) return -1;
        else return dp[y];

    }
}
