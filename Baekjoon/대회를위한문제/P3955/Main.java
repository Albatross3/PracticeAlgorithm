package Baekjoon.대회를위한문제.P3955;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int t;
    static int A,B;
    public static void main(String[] args) throws IOException {
        // X: 인당 나눠줄 사탕의 수
        // Y: 사탕 봉지의 수
        // A * X + 1 = B * Y
        // Ax + By = C 의 형태로 변환
        // -Ax + By = 1
        // A(-x) + By =1 의 형태로 변환

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        t=Integer.parseInt(br.readLine());
        for(int i=0; i<t; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());


            // 1. 해 검증
            // 2. D=gcd(A,B) = eGcd(A,B),
            // Ax + By = C 일때 C % D == 0 이어야 해를 가질 수 있음 : 배주 항등식
            // C % D 1= 0 -> 해가 없음
            EGResult result= extendedGcd(A,B);
            if(result.r!=1){
                System.out.println("IMPOSSIBLE");
            }else {
                // 1.초기 해 구하기
                // x0 = s * C/D
                // y0 = t * C/D
                long x0 = result.s;
                long y0 = result.t;


                // 2. 일반해 구하기
                // x = x0 + B/D*k
                // y = y0 - A/D*K

                // 4.k의 범위 (k는 정수)
                // x < 0
                // x0 + B* k < 0    // gcd=1 이라서 D 생략
                // k < -x0/B

                // 0 < y <= 1e9
                // 0 < y0 - A*k <=1e9
                // -y0 < -A * K <= 1e9 -y0
                // (y0-1e9)/ A <= k < y0/A

                // (y0-1e9)/ A <= k < y0/A
                //                k < -x0/B
                    long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;  // floor() 쓸 수 없다
                    long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;
                    long k = Math.min(kFromY, kFromX);

                    long kLimitFromY = (long) Math.ceil((y0 - 1e9) / (double) A);

                    // 5. 만족하는 K 가 있는지 찾고 K를 통해 Y를 구한다
                    if (kLimitFromY <= k) {
                        System.out.println(y0 - A * k);
                    }
            }
        }
    }

    static EGResult extendedGcd(long a, long b){
        long s0 = 1 ,t0=0, r0=a;
        long s1=0, t1= 1, r1=b;

        long temp;
        while(r1 != 0){
            long q=r0/r1;

            // 유클리드 호제법의 확장
            // 그림을 외워두고 코드로 구현하자
            temp=r0-q*r1;
            r0=r1;
            r1=temp;

            temp=s0-q*s1;
            s0=s1;
            s1=temp;

            temp=t0-q*t1;
            t0=t1;
            t1=temp;
        }
        return new EGResult(s0,t0,r0);
    }
}
class EGResult{
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}