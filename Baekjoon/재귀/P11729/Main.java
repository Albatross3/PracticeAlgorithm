package Baekjoon.재귀.P11729;

// 백준 - 하노이 탑 이동 순서
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        sb.append((int)(Math.pow(2,N)-1)).append("\n");
        hanoi(N,1,2,3);
        System.out.println(sb);
    }
    public static void hanoi(int level,int start,int mid,int to){
        if(level==1){
            sb.append(start+" "+to+"\n");
            return;
        }
        hanoi(level-1,start,to,mid);
        sb.append(start+" "+to+"\n");
        hanoi(level-1,mid,start,to);

    }
}
