package DAY02.P2805;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        tree = new int[N];
        for (int i = 0; i < N; i++)
            tree[i] = Integer.parseInt(st.nextToken());

        //max 찾아주기
//        int max=0;
//        for (int i = 0; i < N; i++) {
//            tree[i]=Integer.parseInt(st.nextToken());
//            max=Math.max(tree[i], max);
//
//        }

        // 최댓값 찾아주기
        Arrays.sort(tree);
        int max = tree[N - 1];
        System.out.println(max);


        int low = 0;
        int high = max;
        int mid = 0;
        int result = 0;
        while (true) {
            int sum = 0;
            mid = (low + high) / 2;
            for (int i = 0; i < N; i++) {
                if (tree[i] < mid)
                    sum += 0;
                else
                    sum += tree[i] - mid;

            }
            System.out.println(sum);
            // sum = M
            if (sum == M) {
                break;
            }
            // sum > M
            else if (sum > M) {
                low = mid;
            }
            // sum < M
            else {
                high = mid;
            }
            //종료 조건
            if (low > high) {
                break;
            }
        }
        System.out.println(mid);

    }
}
//        long s =0 ;
//        long e= max;
//        long mid=0;
//        long result=0;
//        while(true) {
//            mid =(s+e) / 2;
//            long sum=calc(mid);
//            // sum=M ->정답 탈출
//            if (sum==M){
//                result=mid;
//                break;
//            }
//            // sum<M -> mid->end
//            else if (sum<M){
//                e=mid-1;
//            }
//            // sum>M -> mid ->start , 정답 후보
//            else{
//                s=mid+1;
//                result=mid;
//            }
//            //종료 조건
//            if (s>e){
//                break;
//            }
//        }



//    static long calc(long value){
//        long result=0;
//        for (int i = 0; i < tree.length; i++) {
//            int tree=tree[i];
//            if(tree>value){
//                result+=tree-value;
//            }
//
//        }
//        return 0;
//    }

