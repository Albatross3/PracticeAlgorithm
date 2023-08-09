package Baekjoon.조합론.P1010;

// 조합론 - 조합의 성질(파스칼 삼각형)으로 조합 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int N,M;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        // 조합 dp로 구현하기
        dp=new int[29+1][29+1];

        // 1열과 대각선 성분을 모두 1로 만들기
        for(int i=0; i<30; i++){
            dp[i][0]=1;
            dp[i][i]=1;
        }
        // 조합의 성질 이용해서 dp 구현
        for(int i=0; i<30; i++){
            for(int j=0; j<=i; j++){
                if(j!=0 && j!=i){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }
            }
        }

        T=Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            st=new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            sb.append(dp[M][N]).append("\n");
        }
        System.out.println(sb);
    }
}