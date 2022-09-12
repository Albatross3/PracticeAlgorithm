package AlgorithmStudy.투포인터.P11728;

// 두 포인터 종류
// 1. 한 개의 배열에서 2개의 포인터 사용
// 2. 두 개의 배열에서 각각 1개의 포인터 사용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] A;
    static int[] B;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        A=new int[N];
        B=new int[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            B[i]=Integer.parseInt(st.nextToken());
        }

        // 두 포인터 의도대로 풀기
        int index=0;
        int indexA=0;
        int indexB=0;
        result=new int[N+M];
        // 알고리즘 시간복잡도 O(N+M)
        // 둘 중에 큰 것 먼저 넣기
        while(indexA<N && indexB<M){
            if(A[indexA]<=B[indexB]) result[index++]=A[indexA++];
            else result[index++]=B[indexB++];
        }
        // 남아있는 것 넣기
        if(indexA<N) {
            while(indexA<N) {
                result[index++] = A[indexA++];
            }
        }
        if(indexB<M) {
            while(indexB<M) {
                result[index++] = B[indexB++];
            }
        }
        for(int i=0; i<N+M; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
