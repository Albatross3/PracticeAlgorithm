package AlgorithmStudy.정렬.P2217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 물리학 - 로프
public class Main {
    static int N;
    static int[] rope;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        rope=new int[N];
        for (int i = 0; i < N; i++) {
            rope[i]=Integer.parseInt(br.readLine());
        }

        // rope 의 길이 정렬
        Arrays.sort(rope);
        // 최대 중량 구해주는 for 문
        int max=0;
        for(int i=0; i<N; i++){
            int temp=rope[i]*(N-i);
            if(temp>max){
                max=temp;
            }
        }
        System.out.println(max);

    }
}
