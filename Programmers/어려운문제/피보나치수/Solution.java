package Programmers.어려운문제.피보나치수;

// 모듈러 연산을 활용한 문제
public class Solution {
    public int solution(int n) {
        int[] F = new int[n + 1]; // 1234567 로 나눈 나머지가 들어가는 배열
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i < F.length; i++) {
            F[i] = (F[i - 1] % 1234567 + F[i - 2] % 1234567) % 1234567;
        }
        return F[n];
    }
}
