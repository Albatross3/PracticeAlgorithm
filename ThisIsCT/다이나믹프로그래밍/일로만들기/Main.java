package ThisIsCT.다이나믹프로그래밍.일로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int X;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        dp = new int[X+1];
        dp[1]=0;
//        for (int i = 2; i <= X; i++) {
//            List<Integer> arr = new ArrayList<>();
//            if (i % 5 == 0) arr.add(dp[i / 5]);
//            if (i % 3 == 0) arr.add(dp[i / 3]);
//            if (i % 2 == 0) arr.add(dp[i / 2]);
//            arr.add(dp[i-1]);
//
//            Collections.sort(arr);
//
//            dp[i] = arr.get(0)+1;
//
//        }
        for (int i = 2; i <= X; i++) {
            dp[i] = dp[i - 1] ;
            if (i % 5 == 0) dp[i]=Math.min(dp[i],dp[i/5]);
            if (i % 3 == 0) dp[i]=Math.min(dp[i],dp[i/3]);
            if (i % 2 == 0) dp[i]=Math.min(dp[i],dp[i/2]);

            dp[i]+=1;
        }
        System.out.println(dp[X]);
    }
}
