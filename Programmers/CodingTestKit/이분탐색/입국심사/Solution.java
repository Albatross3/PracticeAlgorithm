package Programmers.CodingTestKit.이분탐색.입국심사;

import java.util.Arrays;

public class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long lo = 0, hi = times[times.length - 1] * n;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            if (check(times, mid) < n) lo = mid;
            else hi = mid;
        }
        return hi;
    }

    public static long check(int[] arr, long target) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += target / arr[i];
        }
        return sum;
    }
}