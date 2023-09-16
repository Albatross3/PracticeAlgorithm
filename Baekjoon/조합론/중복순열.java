package Baekjoon.조합론;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 중복순열 {
    static int N, M;
    static int[] repeatedPermutation;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        repeatedPermutation = new int[M];
        getRepeatedPermutation(0);
        System.out.println(sb);
    }
    public static void getRepeatedPermutation(int depth) {
        if (depth == M) {
            for (int i = 0; i < repeatedPermutation.length; i++) {
                sb.append(repeatedPermutation[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            repeatedPermutation[depth] = i;
            getRepeatedPermutation(depth + 1);
        }
    }

}
