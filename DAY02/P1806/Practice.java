package DAY02.P1806;
// 시간복잡도 - 부분합
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Practice {
    static int N,S;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());

        nums=new int[N+1];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }


        int low=0, high=0,sum=nums[0],minLength=100000;
        while(true){
            if(sum >=S){
                minLength=Math.min(high-low+1,minLength);
                sum=sum-nums[low++];
            }
            else{
                sum=sum+nums[++high];
            }
            // 종료 조건(이 부분이
            if (high == N){
                break;
            }
        }

        int sum2=0;
        for(int i=0; i<N; i++){
            sum2+=nums[i];
        }
        if(sum2 <S){
            System.out.println(0);
        }else{
            System.out.println(minLength);
        }
    }
}
