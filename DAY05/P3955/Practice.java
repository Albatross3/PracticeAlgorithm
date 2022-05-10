package DAY05.P3955;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Practice {
    static int t;
    static int K,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        t=Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            K=Integer.parseInt(st.nextToken());
            C=Integer.parseInt(st.nextToken());

            EgcdResult result=extendedGCD(K,C);
            if(result.r != 1){
                System.out.println("IMPOSSIBLE");
            }
            else{
                // 1. 초기해 받아주기
                long x0= extendedGCD(K,C).s;
                long y0= extendedGCD(K,C).t;

                // 2. 일반해 구하고 범위 적용
                long kLimitY = (long)Math.ceil((double)y0/(double)K)-1;
                long kLimitX = (long)Math.ceil((double)-x0/(double)C)-1;
                long k= Math.min(kLimitY,kLimitX);

                long kLimitFromY= (long)Math.ceil((y0-1e9)/(double)K);

                // 만족하는 최대 k를 답으로 출력
                if(kLimitFromY <= k){
                    System.out.println(y0-K*k);
                }else{
                    // 만족안하는 경우 IMPOSSIBLE 출력
                    System.out.println("IMPOSSIBLE");
                }

            }

        }

    }
    static EgcdResult extendedGCD(long A,long B){
        long s0=1, t0=0, r0=A;
        long s1=0, t1=1, r1=B;
        long temp;
        while(r1 != 0){
            long q=r0/r1;

            temp=s0-s1*q;
            s0=s1;
            s1=temp;

            temp=t0-t1*q;
            t0=t1;
            t1=temp;

            temp=r0-r1*q;
            r0=r1;
            r1=temp;
        }
        return new EgcdResult(s0,t0,r0);
    }
}
class EgcdResult{
    long s;
    long t;
    long r;

    // 매개변수 순서를 잘못 써서 한참 헤맴
    public EgcdResult(long s, long t, long r){
        this.s=s;
        this.t=t;
        this.r=r;

    }
}
