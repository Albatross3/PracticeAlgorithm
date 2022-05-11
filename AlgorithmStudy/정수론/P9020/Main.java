package AlgorithmStudy.정수론.P9020;

// 백준 - 골드바흐의 추측
// 1. n이 소수인지 판단하는 함수 활용
// 2. 미리 소수 배열 만들어 놓고 판단

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        T=Integer.parseInt(br.readLine());
        int N=10000;
        boolean[] isNotPrime;
        isNotPrime = new boolean[N+1];
        for(int i=2; i<=Math.sqrt(N); i++){
            if(!isNotPrime[i]){
                for(int j=i+i; j<=N; j+=i)
                    isNotPrime[j]=true;
            }
        }

        int small,large;
        for(int t=0; t<T; t++){
            n=Integer.parseInt(br.readLine());
            small=n/2;
            large=n/2;
            while(true){
                if(!isNotPrime[small] && !isNotPrime[large]) {
                    sb.append(small).append(" ").append(large).append("\n");
                    break;
                }
                small--;
                large++;
            }
        }
        System.out.println(sb);

    }

}
