package Baekjoon.정수론.P1644;
// 정수론 - 소수의 연속합
// 두 포인터 활용
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static List<Integer> primes=new ArrayList<>();
    static boolean[] isNotPrime;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();

        if(N!=1) {
            isNotPrime = new boolean[N + 1];
            for (int i = 2; i < N + 1; i++) {
                if (!isNotPrime[i]) {
                    primes.add(i);
                    for (int j = i * 2; j < N + 1; j += i) {
                        isNotPrime[j] = true;
                    }
                }
            }

            int low=0, high=0, sum=primes.get(0), size=primes.size(), count=0;
            primes.add(0); // ++high 일때 가져와주는 값이 필요함

            while(true){
                // 구간 합이 N보다 클 때
                if(sum>N){
                    sum-=primes.get(low++);
                }
                // 구간 합이 N과 같을 때
                else if(sum==N){
                    count++;
                    sum+=primes.get(++high);
                }
                // 구간 합이 N보다 작을 때
                else{
                    sum+=primes.get(++high);
                }
                // 종료 조건
                if(high==size|| low==size) break;
            }
            System.out.println(count);

        }
        else {
            System.out.println(0);
        }
    }
}
