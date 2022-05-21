package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int N,K;
    static int[] temperature;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        temperature=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            temperature[i]=Integer.parseInt(st.nextToken());
        }

        // 연속적인 합 구하기
        int point1=0;
        int point2=K-1;
        int sum=0;
        for(int i=point1; i<=point2; i++) sum+=temperature[i];
        int MAX=sum;
        while (point2 != N - 1) {
            sum = sum + temperature[++point2] - temperature[point1++];
            MAX = Integer.max(MAX, sum);

        }
        System.out.println(MAX);
    }
}
