package AlgorithmStudy.동적계획법.P12865;

// knapsack problem
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] itemWeight;
    static int[] itemValue;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        itemWeight = new int[N + 1];
        itemValue = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            itemWeight[i + 1] = Integer.parseInt(st.nextToken());
            itemValue[i + 1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K+1];

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < K+1; j++) {
                if (itemWeight[i] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j - itemWeight[i]] + itemValue[i], dp[i - 1][j]);
            }
        }
        System.out.println(dp[N][K]);


    }
}
