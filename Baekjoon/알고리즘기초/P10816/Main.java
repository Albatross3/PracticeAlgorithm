package Baekjoon.알고리즘기초.P10816;

// 정렬 활용 문제
// 정렬 - 이진 탐색
// 정렬 - 2개의 배열 두 포인터
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] card;
    static int M;
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        card=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            card[i]=Integer.parseInt(st.nextToken());
        }

        M=Integer.parseInt(br.readLine());
        number=new int[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            number[i]=Integer.parseInt(st.nextToken());
        }

        // 이진 탐색을 위한 카드 정렬
        Arrays.sort(card);

        // O(M*logN) 의 시간복잡도
        for(int i=0; i<M; i++){
            int left=findLeft(0,N-1, number[i],card);
            int right=findRight(0,N-1,number[i],card);
            if(left==-1 && right==-1) sb.append(0).append(" ");
            else sb.append(right-left+1).append(" ");
        }

        System.out.println(sb);

    }
    // 가장 왼쪽 위치
    static int findLeft(int left,int right, int target, int[] arr){

        // 못 찾은 경우
        if (left > right) return -1;

        int mid=(left+right)/2;

        if((mid==0 || arr[mid-1]<target) && arr[mid]==target) return mid;
        else if(arr[mid] >= target) return findLeft(left,mid-1,target,arr);
        else return findLeft(mid+1,right, target, arr);

    }
    // 가장 오른쪽 위치
    static int findRight(int left, int right,int target, int[] arr){
        // 못 찾은 경우
        if (left > right) return -1;

        int mid=(left+right)/2;

        if((mid==N-1 || arr[mid+1]>target) && arr[mid]==target) return mid;
        else if(arr[mid] > target) return findRight(left,mid-1,target,arr);
        else return findRight(mid+1,right, target, arr);
    }

}
