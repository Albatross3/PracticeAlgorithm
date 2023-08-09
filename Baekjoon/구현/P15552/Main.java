package Baekjoon.구현.P15552;

import java.io.*;
import java.util.StringTokenizer;

// 구현 - 빠른 A+B
// BufferedReader 와 BufferedWriter 의 연습
public class Main {
    static int T;
    static int A,B;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        T=Integer.parseInt(st.nextToken());
        for(int i=0; i<T; i++){
            st=new StringTokenizer(br.readLine());
            A=Integer.parseInt(st.nextToken());
            B=Integer.parseInt(st.nextToken());
            bw.write(A+B+ "\n"); //버퍼에 있는 값 전부 출력

        }
        bw.flush(); // 남아있는 데이터를 모두 출력시킴
        bw.close(); // 스트림을 닫음
    }
}
