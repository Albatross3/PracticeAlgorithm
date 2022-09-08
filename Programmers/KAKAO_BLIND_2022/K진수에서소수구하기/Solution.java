package Programmers.KAKAO_BLIND_2022.K진수에서소수구하기;
// 9:15 완료 -> 런타임에러
// 원인 : 돌아오는 값이 엄청 길 수 있음
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        String s = "101";
        String[] arr = s.split("0");
        for (String temp : arr) {
            System.out.println(temp);
        }
        System.out.println(changeToKNotation(437674,3));
    }
    public int solution(int n, int k) {
        int count = 0;
        String kNotation = changeToKNotation(n, k);
        String[] arr = kNotation.split("0");
        for (String s : arr) {
            if(s.equals("")) continue;
            long num = Long.parseLong(s);
            if(isPrime(num)) count++;
        }
        return count;
    }

    public static boolean isPrime(long number) {
        if(number==1) return false;
        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static String changeToKNotation(int number, int K) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if(number<K) {
                sb.append(number);
                break;
            }
            sb.append(number % K);
            number = number / K;
        }
        return sb.reverse().toString();
    }
}
