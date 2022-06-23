package AlgorithmStudy;

// 세그먼트 트리 -> 구간 합 구하기 or 값 수정
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int N,Q;
    static int x,y,a,b;
    static int[] num;
    static int[] cumulativeSum;
    static long sum;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        Q=Integer.parseInt(st.nextToken());

        num=new int[N];
        cumulativeSum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        int cSum=0;
        for (int i = 1; i < N+1; i++) {
            cSum+=num[i-1];
            cumulativeSum[i]=cSum;
        }
        for (int i = 0; i < Q; i++) {
            sum=0;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(y>=x){
                sb.append(cumulativeSum[y]-cumulativeSum[x-1]).append("\n");
            }
            else{
                sb.append(cumulativeSum[x]-cumulativeSum[y-1]).append("\n");
            }

            num[a-1]=b;
        }
        System.out.println(sb);
    }
}
