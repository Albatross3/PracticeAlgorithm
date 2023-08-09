package Baekjoon.정수론.P2960;

// 2022/5/11 refactoring
// 에라토스테네스의 체 - 소수 찾기 알고리즘

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static boolean[] isNotPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[N + 1];
        int k = 0;
        loop:
        for (int i = 2; i <= N; i++) {
            for (int j = i ; j < N + 1; j += i) {
                if (isNotPrime[j] == false) {
                    isNotPrime[j] = true;
                    k++;
                }
                if (k == K) {
                    System.out.println(j);
                    break loop;
                }
            }

        }
    }
}
