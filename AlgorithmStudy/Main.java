package AlgorithmStudy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a,b,c;
    static int[][][] dp = new int[21][21][21];
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // w[0][0][0]~w[20][20][20]
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                for (int k = 0; k < 21; k++) {
                    if( i==0 || j==0 || k==0) dp[i][j][k]=1;
                    else if(i<j && j<k) dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1] - dp[i][j - 1][k];
                    else dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] - dp[i - 1][j - 1][k - 1];
                }
            }
        }

        while(true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            // 종료 조건
            if(a==-1 && b==-1 && c==-1) break;

            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
            // 출력
            if (a <= 0 || b <= 0 || c <= 0) {
                sb.append(1).append("\n");
            } else if (a > 20 || b > 20 || c > 20) {
                sb.append(dp[20][20][20]).append("\n");
            } else sb.append(dp[a][b][c]).append("\n");
        }
        System.out.println(sb);
    }

}