package Programmers.KAKAO_BLIND_2021.합승택시요금;

public class Solution {
    static int[][] dp;
    static int MAX = 1000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dp = new int[n+1][n+1];
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(i!=j) dp[i][j] = MAX;
            }
        }
        for(int[] fare : fares) {
            int v1 = fare[0];
            int v2 = fare[1];
            int c = fare[2];
            dp[v1][v2] = c;
            dp[v2][v1] = c;
        }

        for(int k=1; k<n+1; k++) {
            for(int i=1; i<n+1; i++) {
                for(int j=1; j<n+1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int answer = dp[s][a] + dp[s][b];
        for(int k=1; k<n+1; k++) {
            answer = Math.min(answer, dp[s][k] + dp[k][a] + dp[k][b]);
        }

        return answer;
    }
}
