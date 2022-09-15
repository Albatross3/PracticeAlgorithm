package Programmers.연습문제.최소공배수;

public class Solution {
    public int solution(int[] arr) {
        int LCM=1;
        int GCD=0;
        for (int i = 0; i < arr.length; i++) {
            GCD = gcd(LCM, arr[i]);
            LCM = (LCM * arr[i]) / GCD;
        }
        return LCM;
    }
    // 최대 공약수
    public static int gcd(int num1, int num2) {
        if(num2==0) return num1;
        else return gcd(num2, num1 % num2);
    }
}
