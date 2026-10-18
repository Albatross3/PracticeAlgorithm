package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, P;
    static int[] breads;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        breads = new int[N*K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * K; i++) {
            breads[i] = Integer.parseInt(st.nextToken());
        }

        // Logic
        for (int i = 0; i < N * K; i += K) {
            // K 개에 대하여 확인

            // 불량품 배열 생성
            int[] partialArray = new int[K];
            for (int j = 0; j < K; j++) {
                partialArray[j] = breads[i + j];
            }

            // 정상/불량 검수
            if (defectInspection(partialArray, P)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean defectInspection(int[] arr, int num) {
        int x = 0; // 불량품 개수
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x++;
            }
        }
        return x < num;
    }
}
