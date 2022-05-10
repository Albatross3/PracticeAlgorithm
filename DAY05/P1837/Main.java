package DAY05.P1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
//    static String P;
//    static int K;
//    static Boolean[] isPrime;

    // pro님 풀이
    static int MAX=1000000;
    static int K;
    static char[] P;
    static  boolean[] isNotPrime;
    static List<Integer> primes= new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st=new StringTokenizer(br.readLine());
//
//        P=st.nextToken();
//        K=Integer.parseInt(st.nextToken());

        // k 보다 작은 소수를 모두 구하기

        // 에라토스테네스의 체를 사용하기 위한 숫자 배열 마련
//        isPrime=new Boolean[K-1];
//        for (int i = 0; i < isPrime.length; i++) {
//            isPrime[i]=false;
//            int count=0;
//            for(int j=1; j<=i+1; j++){
//                if((i+1) % j ==0){
//                    count++;
//                }
//            }
//            if (count==2) isPrime[i]=true;
//        }
        // pro님 풀이
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        P=st.nextToken().toCharArray(); // string형 을 char형 배열로 바꾸어준다
        K=Integer.parseInt(st.nextToken());

        isNotPrime=new boolean[MAX+1];

        // 시간 복잡도>? O(max * alpha)
        for(int i=2; i<MAX+1; i++){
            if(!isNotPrime[i]){
                primes.add(i);
                for(int j=i*2; j<MAX+1; j+=i){
                    isNotPrime[j] =true;
                }
            }
        }

        for(int prime:primes){
            if((prime) >= K) {
                break;
            }
            // k 보다 작은 소수들로 P기 나누어지는지 check 하는 함수
            if (checkIsBad(prime)){
                System.out.println("BAD");
                return;
            }
        }
        System.out.println("GOOD");

    }
    static boolean checkIsBad(int x) {
        int r=0;

        for(int i=0; i<P.length; i++){
            r= (r*10 +(P[i]-'0')) % x;
        }
        if(r ==0){
            return true;
        }
        else{
            return false;
        }
    }

}
