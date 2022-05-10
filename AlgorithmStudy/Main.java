package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int num=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        while(true){
            n=Integer.parseInt(br.readLine());
            if(n==0) break;
            // 2n 이하의 소수 개수 찾기
            boolean isPrime[]=new boolean[2*n+1];
            for(int i=2; i<=2*n; i++)
                isPrime[i]=true;

            for(int i=2; i<=Math.sqrt(2*n); i++){
                // 소수라면 소수의 배수 지우기
                if(isPrime[i]){
                    for(int x=2*i; x<=2*n; x+=i){
                        isPrime[x]=false;
                    }
                }
            }
            int numPrime=0;
            for(int i=n+1; i<=2*n; i++){
                if(isPrime[i]) numPrime++;
            }
            sb.append(numPrime).append("\n");
        }
        System.out.println(sb);
    }
}
