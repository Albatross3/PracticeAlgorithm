package DAY08.P2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 동적 계획법 1 - 계단 오르기
public class Main {
    static int N;
    static int[] number;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        number=new int[N+1];
        for(int i=1; i<number.length; i++){
            number[i]=Integer.parseInt(br.readLine());
        }

        dp=new int[N+1];
        // dp[n]=max(dp[n-2],dp[n-3]+number[n-1])+number[n] (n>=3) , 초깃값
        // n=1 일때 예외 존재
        dp[1]=number[1];
        if(N>1){
            dp[2]=number[1]+number[2];
            for(int i=3; i<N+1; i++){
                dp[i]=Math.max(dp[i-2],dp[i-3]+number[i-1])+number[i];
            }
            System.out.println(dp[N]);
        }else{
            System.out.println(dp[1]);
        }

    }
}

//package BOJ;
//
//        import java.io.*;
//
///**
// *
// * @author s_cheol.park
// * https://www.acmicpc.net/problem/2579
// * 계단오르기 - DP
// * 시간복잡도 O(N)
// */
//
//public class P_2579_계단오르기 {
//
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int N = Integer.parseInt(br.readLine());
//        int Score [] = new int [N+1];
//        int D [][] = new int [N+1][2+1]; // D[i][j] = i번째 계단을 밟으면서 (j개 계단 연속으로 밟으면서) 가질수 있는 가장 큰 값
//
//        // 입력
//        for(int i = 1 ; i <= N ; i++) {
//            Score[i] = Integer.parseInt(br.readLine());
//        }
//
//        // 초기화 점화식을 이용한 계산
//        D[1][1] = Score[1];
//
//        for(int i = 2 ; i <= N ; i++) {
//            D[i][1] = Math.max(D[i][1], D[i - 2][1] + Score[i]); // 2칸 올라오면서 밟는경우 (직전 연속1칸)
//            D[i][1] = Math.max(D[i][1], D[i - 2][2] + Score[i]); // 2칸 올라오면서 밟는경우 (직전 연속2칸)
//            D[i][2] = Math.max(D[i][2], D[i - 1][1] + Score[i]); // 1칸 올라오면서 밟는경우 (직전 연속1칸)
//            // 1칸올라가면서 연속2칸이 직전인 케이스는 없음
//        }
//
//        // 정답찾기
//        bw.write(Math.max(D[N][1],D[N][2]) + "\n");
//        bw.flush();
//        bw.close();
//
//    }
//}
