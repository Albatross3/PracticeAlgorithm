package Baekjoon.조합론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 중복조합 {
    static int N, M;
    static int[] repeatedCombination;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        repeatedCombination = new int[M + 1];
        getRepeatedCombination(0);
        System.out.println(sb);
    }

    public static void getRepeatedCombination(int depth) {
        if (depth == M) {
            for (int i = 1; i < repeatedCombination.length; i++) {
                sb.append(repeatedCombination[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (repeatedCombination[depth] <= i) {
                repeatedCombination[depth + 1] = i;
                getRepeatedCombination(depth + 1);
            }
        }
    }
}
