package Baekjoon.정수론.P2609;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int X,Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());

        int gcd=gcd(X,Y);
        System.out.println(gcd);
        System.out.println(X*Y/gcd);

    }
    // 재귀 방법
    static int gcd(int a,int b){
        // 종료 조건
        int r=a%b;
        if(r==0) return b;
        // 반복
        else return gcd(b,r);
    }
    // 반복문 방법
    static int gcd2(int a,int b){
        while(b!=0){
            int r=a%b;
            a=b;
            b=r;
        }
        return a;
    }

}
