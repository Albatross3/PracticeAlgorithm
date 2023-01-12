package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    static int N;
    static int[] T;
    static int[] P;
    static int[] dp;

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            T[i] = Integer.parseInt(s[0]);
            P[i] = Integer.parseInt(s[1]);
        }


        for (int i = N - 1; i >= 0; i--) {
            int next = i + T[i];
            if (next <= N) {
                dp[i] = Math.max(dp[i + 1], P[i] + dp[next]);
            } else {
                dp[i] = dp[i + 1];
            }
        }
        System.out.println(dp[0]);

    }

}
