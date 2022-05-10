package DAY02.P2003;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] A;
    public static void main(String[] args) throws IOException {
        //성능을 위해서 이러한 입력 필요
        System.setIn(new FileInputStream("src/DAY02/P2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(N);
        System.out.println(M);
        A = new int[N + 1];  //하나 크게 만들어준다
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());

        }


        //알고리즘 시작
        int L=0,H=0;
        int sum=0;
        int result=0;


        while (H <= N-1 ){
            if (L==H) {
                sum = A[L];
                if (sum == M) {
                    result = result + 1;
                    L++;
                    H++;
                } else if (sum < M) {
                    H++;
                } else {
                    L++;
                    H++;
                }

            }

            else{
                    for(int i=L; i<=H; i++)
                        sum+=A[i];

                    if (sum==M){
                        result=result+1;
                        L++;
                    }
                    else if (sum<M){
                        H++;
                    }
                    else {
                        L++;
                    }
                }

            }
        System.out.println(result);
        System.out.println("Hello world");
        /*
        //while(true) 안에 밑에 내용 있음
        int low=0, high=0, sum=A[0], count=0;
        if(sum ==M){
            count++;
            sum-=A[low++];
        }
        else if (sum>M){
           sum-=A[low++];
        }
        else{
            sum+=A[++high];
        }
        if (high==N) {
            break;
        }
         */
    }
}
