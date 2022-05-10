package AlgorithmStudy.구현.P1065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        System.out.println(hansu(N));
    }
    static int hansu(int n){
        int a,b,c;
        int result=0;
        if(n<100){
            return n;
        }
        else {
            for(int i=100; i<=n; i++) {
                a=i/100;
                b=(i/10)%10;
                c=i%10;
                if (2 * b == a + c) {
                    result++;
                }
            }
            return result+99;
        }
    }
}
