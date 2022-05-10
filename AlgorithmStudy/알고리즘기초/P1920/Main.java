package AlgorithmStudy.알고리즘기초.P1920;

// 탐색 - 이진 탐색 (전제조건: 정렬)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] A;
    static int[] Q;
    public static void main(String[] args) throws IOException {
        //이진 탐색 -> while문 구현 or 재귀로 구현
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        A=new int[N];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i]=Integer.parseInt(st.nextToken());
        }
        M=Integer.parseInt(br.readLine());
        Q=new int[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            Q[i]=Integer.parseInt(st.nextToken());
        }
        //이진 탐색을 위한 정렬 (오름차순)
        Arrays.sort(A);
        //이진 탐색 - while로 구현


        for(int i=0; i<M; i++){
            int start=0; int end=N-1;
            boolean isFound=false;
            while(start <= end){
                int mid=(start+end)/2;
                if(A[mid]==Q[i]){
                    isFound=true;
                    System.out.println(1);
                    break;
                }
                // 찾는 놈이 중간놈보다 작은 경우
                else if (A[mid] > Q[i] ){
                    end=mid-1;
                }
                // 찾는 놈이 중간놈보다 큰 경우
                else{
                    start=mid+1;
                }
            }
            if(isFound==false){
                System.out.println(0);
            }
        }
    }
}
