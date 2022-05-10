package DAY02.P1806;

import java.io.*;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class Main {
    static int N,S;
    static int[] nums;
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/DAY02/P1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());

        nums=new int[N+1];
        System.out.println(nums[N]);
        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i]=Integer.parseInt(st.nextToken());
        }
        int low=0,high=0, sum=nums[0], minLength =Integer.MAX_VALUE;
        while (true) {
            if (sum >= S){
                minLength=Math.min(high-low+1,minLength);
                sum-=nums[low++];

            }else {
                sum+=nums[++high];
            }
            if (high==N){
                break;
            }
        }
    }
}
