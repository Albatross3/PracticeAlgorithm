package AlgorithmStudy.기타.P2003;

// 두 포인터 - 수들의 합 2
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        number=new int[N+2];
        for(int i=1; i<N+1; i++){
            number[i]=Integer.parseInt(st.nextToken());
        }

        number[N+1]=0;
        int low=1, high=1, sum=number[1], size= number.length, count=0;
        while(high!=N+1){
            // 같은 경우
            if(sum==M){
                count++;
                sum+=number[++high];
            }
            // 큰 경우
            else if(sum > M){
                sum-=number[low++];
            }
            // 작은 경우
            else{
                sum+=number[++high];
            }
        }
        sb.append(count);
        System.out.println(sb);
    }

}
