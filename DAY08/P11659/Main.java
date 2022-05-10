package DAY08.P11659;

import java.io.*;
import java.util.StringTokenizer;

// 동적 계획법 1 - 구간 합 구하기 4
public class Main {
    static int N,M;
    static int[] number;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        number=new int[N+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            number[i]=Integer.parseInt(st.nextToken());
        }

        //dp 생성 (부분 누적합) ,  dp[n] : n번째 수까지의 누적합 , dp[n]=dp[n-1]+number[n] , dp[1]=number[1]
        dp=new int[N+1];
        dp[1]=number[1];
        for(int i=2; i<N+1; i++){
            dp[i]=dp[i-1]+number[i];
        }

        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            bw.write(dp[end]-dp[start-1]+"\n");
        }
        bw.flush();
        bw.close();
    }
}


//package BOJ;
//
//        import java.io.*;
//        import java.util.*;
//
///**
// *
// * @author s_cheol.park
// * https://www.acmicpc.net/problem/11659
// * 구간 합 구하기 4 - DP
// * O(N + M)
// */
//
//public class P_11659_구간합구하기4 {
//
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        StringTokenizer st;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        st = new StringTokenizer(br.readLine(), " ");
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int Number [] = new int [N+1];
//        int D [] = new int [N+1];
//
//        // 입력
//        st = new StringTokenizer(br.readLine(), " ");
//        for(int i = 1 ; i <= N ; i++) {
//            Number[i] = Integer.parseInt(st.nextToken());
//        }
//
//        // 점화식을 이용한 계산
//        for(int i = 1 ; i <= N ; i++) {
//            D[i] = Number[i] + D[i-1];
//        }
//
//        // 정답찾기
//        int from, to, Answer;
//        for(int i = 1 ; i <= M ; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            from = Integer.parseInt(st.nextToken());
//            to = Integer.parseInt(st.nextToken());
//            Answer = D[to] - D[from - 1];
//            bw.write(Answer + "\n");
//        }
//        bw.flush();
//        bw.close();
//
//    }
//}