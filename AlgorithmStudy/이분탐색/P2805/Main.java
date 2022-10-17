package AlgorithmStudy.이분탐색.P2805;
// 1. 굳이 정렬 안해도 됨
// 2. check 함수 호출 부분을 분리하지 않으면 좀 더 빨라지긴 함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] trees;
    static int MAX = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        // 이분 탐색을 위한 정렬
        for(int i=0; i<N; i++) {
            MAX = Math.max(trees[i], MAX);
        }
        System.out.println(binarySearch(trees));
    }
    // 1. check(start)!=check(end) 성립 -> T ~ F 분포
    // 2. start 와 end 일 때 모든 범위를 포함할 수 있는지 확인 -> o
    // 3. T를 만족하는 최대의 cut 구하기
    public static int binarySearch(int[] arr) {
        int start = 0, end = MAX;
        while (start + 1 < end) {
            int middle = (start + end) / 2;
            if (check(arr,middle)) {
                start = middle ;
            } else {
                end = middle;
            }
        }
        return start;
    }
    // check - 결정 함수
    public static boolean check(int[] arr,int cut) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= cut) sum += arr[i] - cut;
        }
        return sum >= M;
    }

}
