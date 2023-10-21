package Baekjoon.이분탐색.P12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        list.add(A[0]);
        for (int i = 1; i < N; i++) {
            if (list.get(list.size()-1) < A[i]) {
                list.add(A[i]);

            } else {
                // lower bound 에 해당하는 index 찾고 교체
                int index = findLowerBoundIndex(A[i]);
                list.set(index, A[i]);
            }
        }
        System.out.println(list.size());
    }

    static int findLowerBoundIndex(int x) {
        int lo = -1;
        int hi = list.size();

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) < x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}
