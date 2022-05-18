package AlgorithmStudy.이분탐색.P10815;
// 백준 - 숫자카드
// 이분탐색 구현 방법 2가지
//      1. while 문
//      2. 재귀방법

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] card;
    static int[] query;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());
        card=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            card[i]=Integer.parseInt(st.nextToken());
        }
        M=Integer.parseInt(br.readLine());
        query=new int[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            query[i]=Integer.parseInt(st.nextToken());
        }

        // card 정렬
        Arrays.sort(card);
        // 이진 탐색
        for(int i=0; i<M; i++){
            sb.append(binarySearch(card,query[i])).append(" ");
        }
        System.out.println(sb);
    }
    public static int binarySearch(int[] arr, int target){
        int start=0,end=arr.length-1;

        while(start<=end){
            int middle=(start+end)/2;
            if(target<arr[middle]) {
                end=middle-1;
            }
            else if(target>arr[middle]){
                start=middle+1;
            }else{
                return 1;
            }
        }
        return 0;
    }

}
