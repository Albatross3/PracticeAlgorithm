package Programmers.연습문제.정수삼각형;

public class Solution {
    public int solution(int[][] triangle) {
        int length = triangle.length;
        int[][] dp = new int[length][length];
        dp[0][0] = triangle[0][0];
        for(int i=1; i<length; i++) {
            // 가장 왼쪽
            dp[i][0] = dp[i-1][0] + triangle[i][0];

            // 중앙
            for(int j=1; j<i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }

            // 가장 오른쪽
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
        }

        int answer = 0;
        for(int i=0; i<length; i++) {
            answer = Math.max(answer, dp[length-1][i]);
        }
        return answer;
    }
}
