package Baekjoon.구현.P1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M,N;
    static char[][] chess;
    static char[][] chess_B={{'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'}};

    static char[][] chess_W={{'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'},
                             {'W','B','W','B','W','B','W','B'},
                             {'B','W','B','W','B','W','B','W'}};
    static char[][] board;
    static int MIN=32;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        int m=M-8+1; // 행으로 반복해야하는 횟수
        int n=N-8+1; // 열로 반복해야하는 횟수
        int I=0;
        int J=0;
        chess=new char[8][8];
        board=new char[M][N];
        for(int i=0; i<M; i++){
            String s=br.readLine();
            for(int j=0; j<N; j++){
                board[i][j]=s.charAt(j);
            }
        }

        while(true){

            int countB=0;
            int countW=0;
            int min;
            for(int i=I; i<I+8; i++){
                for(int j=J; j<J+8; j++){
                    chess[i-I][j-J]=board[i][j];
                }
            }
            // 맨 왼쪽 B or W 일때 바꿔야하는 횟수 중 최소
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(chess[i][j]!=chess_B[i][j]) countB++;
                    if(chess[i][j]!=chess_W[i][j]) countW++;
                }
            }
            min=Math.min(countB,countW);

            // chess판 바꾸어가면서 최솟값을 저장함
            MIN=Math.min(min,MIN);

            // 반복 횟수 조절
            J++;
            if(J==n){
                I++;
                J=0;
            }
            if(I==m){
                break;
            }
        }
        System.out.println(MIN);
    }
}
