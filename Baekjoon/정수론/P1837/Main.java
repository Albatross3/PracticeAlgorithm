package Baekjoon.정수론.P1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 암호 제작
public class Main {
    static char[] P;
    static int K;
    static boolean[] isPrime;
    static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        // 에라토스테네스의 체
        isPrime = new boolean[K];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < K; i++) {
            primes.add(i);
            if (isPrime[i]) {
                // i 의 배수들은 소수가 아님
                for (int j = 2 * i; j < isPrime.length; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        // P 를 소수들로 나누기
        for (int prime : primes) {
            // BAD 의 경우
            if (isDivided(prime)) {
                System.out.println("BAD" + " " + prime);
                return;
            }
        }
        System.out.println("GOOD");
    }

    public static boolean isDivided(int prime) {
        int r = 0;
        for (char c : P) {
            r = (r * 10 + (c - '0')) % prime;
        }
        return r == 0;
    }
}
