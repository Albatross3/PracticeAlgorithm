package AlgorithmStudy.정수론.P1735;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A1,A2,B1,B2;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        A1=Integer.parseInt(st.nextToken());
        B1=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        A2=Integer.parseInt(st.nextToken());
        B2=Integer.parseInt(st.nextToken());

        int A=A1*B2+A2*B1;
        int B=B1*B2;
        if(gcd(A,B)==1){
            System.out.println(A+" "+B);
        }else{
            System.out.println(A/gcd(A,B)+" "+B/gcd(A,B));
        }
    }
    static int gcd(int a,int b){
        while(b!=0){
            int r=a%b;
            a=b;
            b=r;
        }
        return a;
    }
}
