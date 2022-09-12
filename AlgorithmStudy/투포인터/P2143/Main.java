package AlgorithmStudy.투포인터.P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int N, M;
    static int[] A;
    static int[] B;

    static ArrayList<Integer> sumA = new ArrayList<>();
    static ArrayList<Integer> sumB = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N + 2];
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        B = new int[M + 2];
        for (int i = 1; i < M + 1; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A와 B의 부분합을 저장
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = i; j < N + 1; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }
        for (int i = 1; i < M + 1; i++) {
            int sum = 0;
            for (int j = i; j < M + 1; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }
        Collections.sort(sumA);
        Collections.sort(sumB);

        int count=0;
        for (int i = 0; i < sumA.size(); i++) {
            count += upperBound(sumB, T - sumA.get(i)) - lowerBound(sumB, T - sumA.get(i));
        }
        System.out.println(count);
    }

    public static int lowerBound(ArrayList<Integer> arr, int target) {
        int start = 0, end = arr.size();
        while (start < end) {
            int middle = (start + end) / 2;
            if (arr.get(middle) < target) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return end;
    }

    public static int upperBound(ArrayList<Integer> arr, int target) {
        int start = 0, end = arr.size();
        while (start < end) {
            int middle = (start + end) / 2;
            if (arr.get(middle) <= target) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return end;
    }
}
