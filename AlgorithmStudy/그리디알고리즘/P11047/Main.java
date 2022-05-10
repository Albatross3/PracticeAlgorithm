package AlgorithmStudy.그리디알고리즘.P11047;

// 그리디 알고리즘 - 동전
//  A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수 -> 그리디 적용 가능
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int N,K;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        A=new int[N];
        for(int i=0; i<N; i++){
            A[i]=Integer.parseInt(br.readLine());
        }

        // 그리디 알고리즘
        int result=0;
        for(int i=N-1; i>=0; i--){
            if(A[i]<=K){
                result+=K/A[i];
                K=K%A[i];
            }
            if(K==0) break;
        }
        System.out.println(result);
    }
}
