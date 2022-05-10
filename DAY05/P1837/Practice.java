package DAY05.P1837;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Practice {
    static char[] P;
    static int K;
    static List<Integer> primes=new ArrayList<>();
    static boolean[] isNotPrime;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        P=sc.next().toCharArray();
        K=sc.nextInt();

        isNotPrime=new boolean[K+1];
        for(int i=2; i<K; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (int j = i*2; j <K; j+=i) {
                    isNotPrime[j] = true;
                }
            }
        }
        boolean result=true;

        for (int prime:primes){
            if(isDivided(prime)){
                result=false;
                System.out.println("BAD" +" "+prime);
                break;
            }
        }

        if(result==true){
            System.out.println("GOOD");
        }
    }
    static boolean isDivided(int x){
        int r=0;

        for(int i=0; i<P.length; i++){
            r=(r*10+(int)(P[i]-'0')) % x;
        }
        if(r==0) return true;
        else return false;
    }
}
