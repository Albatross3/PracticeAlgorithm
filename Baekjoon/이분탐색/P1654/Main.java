package Baekjoon.이분탐색.P1654;
// 기본적인 이분탐색 문제
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int K, N;
    static long[] lanCable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lanCable = new long[K];
        for (int i = 0; i < K; i++) {
            lanCable[i] = Integer.parseInt(br.readLine());
        }
        // 이분 탐색을 위한 정렬
        Arrays.sort(lanCable);
        System.out.println(binarySearch(lanCable));
    }
    // 이분 탐색
    // check(start)==T , check(end)==F -> 반드시 달라야 함
    public static long binarySearch(long[] arr) {
        long start = 1;
        long end = arr[arr.length - 1]+1;
        while (start + 1 < end) {
            long mid = (start + end) / 2;
            if (check(mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return start;
    }
    // 결정 함수
    public static boolean check(long length) {
        long sum=0;
        for (int i = 0; i < lanCable.length; i++) {
            sum += lanCable[i] / length;
        }
        return sum >= N;
    }
}
