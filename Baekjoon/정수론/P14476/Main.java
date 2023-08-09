package Baekjoon.정수론.P14476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[] gcdLeftToRight;
    static int[] gcdRightToLeft;
    public static void main(String[] args) throws IOException {
        // 작은 순서대로 정렬 -> 후에 차례로 gcd 구하기
        // 반복이 된다면, 겹치는 부분이 생긴다면 -> 메모제이션 기법을 떠올리기 (DP, 누적합)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        gcdLeftToRight = new int[N];
        gcdRightToLeft = new int[N];

        gcdLeftToRight[0] = nums[0];
        gcdRightToLeft[N - 1] = nums[N - 1];

        // 왼쪽에서 오른쪽으로 가면서 gcd 차례로 계산해두기
        // 오른쪽에서 왼쪽으로 가면서 gcd 차례로 계산해두기
        for (int i = 1; i < nums.length; i++) {
            gcdLeftToRight[i] = greatestCommonDivisor(gcdLeftToRight[i - 1], nums[i]);
        }
        for (int i = N - 2; i >= 0; i--) {
            gcdRightToLeft[i] = greatestCommonDivisor(gcdRightToLeft[i + 1], nums[i]);
        }

        int max = -1;
        int k = -1;
        for (int i = 0; i < N; i++) {
            int tempMax;
            if (i == 0) {
                tempMax = gcdRightToLeft[1];
            } else if (i == N - 1) {
                tempMax = gcdLeftToRight[N-2];
            } else {
                tempMax = greatestCommonDivisor(gcdLeftToRight[i - 1], gcdRightToLeft[i + 1]);
            }
            if (nums[i] % tempMax == 0) continue;

            if(tempMax > max) {
                max = tempMax;
                k = nums[i];
            }
        }
        if(max == -1) System.out.println(-1);
        else {
            System.out.print(max + " " + k);
        }
    }

    public static int greatestCommonDivisor(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return greatestCommonDivisor(b, a % b);
    }
}
