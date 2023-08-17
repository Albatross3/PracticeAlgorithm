package Baekjoon.백트래킹.P1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] number;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        number = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        getCombination(0, 0);
        if(S==0) count--;
        System.out.println(count);
    }

    public static void getCombination(int depth, int sum) {
        if (depth == N) {
            if (sum == S) {
                count++;
            }
            return;
        }

        getCombination(depth + 1, sum);
        getCombination(depth + 1, sum + number[depth]);
    }
}
