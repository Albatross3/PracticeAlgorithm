package Baekjoon.이분탐색.P2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C;
    static int[] housePosition;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        housePosition = new int[N];
        for (int i = 0; i < N; i++) {
            housePosition[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(housePosition);

        System.out.println(findMaxDistance());

    }

    static int findMaxDistance() {
        int lo = 0;
        int hi = 1000000000 + 1;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (checkPossible(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    static boolean checkPossible(int distance) {
        int sum = 1;
        int prev = housePosition[0];
        for (int i = 1; i < housePosition.length; i++) {
            if (housePosition[i] - prev >= distance) {
                prev = housePosition[i];
                sum++;
            }
        }
        return sum >= C;
    }
}
