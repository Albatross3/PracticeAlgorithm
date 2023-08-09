package Baekjoon.동적계획법.P14501;

// 삼성 SW 역량테스트 기출문제
// 백준 - 퇴사, DP를 언제 사용하는지 판단하는 연습
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] T;
    static int[] P;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        T=new int[N];
        P=new int[N];
        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            T[i]=Integer.parseInt(st.nextToken());
            P[i]=Integer.parseInt(st.nextToken());
        }
        dp=new int[N+1];
        dp[N]=0;
        if(T[N-1]==1) dp[N-1]=P[N-1];
        else dp[N-1]=0;
        for(int i=N-2; i>=0; i--){
            int temp=i+T[i];
            if (temp >N) dp[i]=dp[i+1];
            else if (temp==N) dp[i]=Math.max(P[i],dp[i+1]);
            else dp[i]=Math.max(P[i]+dp[temp],dp[i+1]);
        }
        System.out.println(dp[0]);
    }
}
