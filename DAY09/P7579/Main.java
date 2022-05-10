package DAY09.P7579;

import java.io.*;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

// 동적 계획법 2 - 앱
public class Main {
    static int N,M;
    static int[] m;
    static int[] c;
    static int[][] DP;
    static int totalCost=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        m=new int[N+1];
        c=new int[N+1];

        // N개의 앱 메모리 입력 받기 (1 ~ N)
        st=new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            m[i]=Integer.parseInt(st.nextToken());
        }
        // N개의 앱 비용 입력 받기 (1 ~ N)
        st=new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            c[i]=Integer.parseInt(st.nextToken());
            totalCost+=c[i];
        }

        // DP 생성해주기, 점화식
        // DP[1][j] 일 때 먼저 만들어주기
        // DP[i][j] ( i >= 2) 일 때의 점화식 만들기
        DP=new int[N+1][totalCost+1];
        for(int j=0; j<totalCost+1; j++){
            if(j-c[1]>=0) DP[1][j]=m[1];
        }

        for(int i=2; i<N+1; i++){
            for(int j=0; j<totalCost+1; j++){
                DP[i][j]=DP[i-1][j];

            }
        }
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
// * https://www.acmicpc.net/problem/7579
// * 앱 - DP
// * O(N * Sum of Cost)
// * 냅색스타일의 문제
// * DP[i][j] = i 번 앱까지 중, j 만큼의 비용을 사용하여 만들수 있는 가장 많은 메모리
// */
//
//public class P_7579_앱 {
//
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        StringTokenizer st;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        st = new StringTokenizer(br.readLine(), " ");
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int TotalCost = 0;
//        int Memory [] = new int [N+1];
//        int Cost [] = new int [N+1];
//
//        // 입력
//        st = new StringTokenizer(br.readLine(), " ");
//        for(int i = 1 ; i <= N ; i++) {
//            Memory[i] = Integer.parseInt(st.nextToken());
//        }
//        st = new StringTokenizer(br.readLine(), " ");
//        for(int i = 1 ; i <= N ; i++) {
//            Cost[i] = Integer.parseInt(st.nextToken());
//            TotalCost += Cost[i];
//        }
//        int DP[][] = new int [N+1][TotalCost+1]; // DP[i][j] = i 번 앱까지 중, j 만큼의 비용을 사용하여 만들수 있는 가장 많은 메모리
//
//        // 점화식을 이용한 계산
//        int Answer = TotalCost+1;
//        for(int i = 1 ; i <= N ; i++) {
//            for(int j = 0 ; j <= TotalCost; j++) {
//                DP[i][j] = Math.max(DP[i][j],DP[i-1][j]); // 기초값
//                if(j - Cost[i] >= 0) {
//                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - Cost[i]] + Memory[i]);
//                }
//            }
//        }
//
//        for(int i = 1 ; i <= TotalCost ; i++) {
//            if(DP[N][i] >= M) {
//                Answer = i;
//                break;
//            }
//        }
//
//        // 정답찾기
//        bw.write(Answer + "\n");
//        bw.flush();
//        bw.close();
//
//    }
//}