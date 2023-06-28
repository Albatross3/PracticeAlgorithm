package AlgorithmStudy.인덱스트리.P2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// bottom-up 방식 - 인덱스 트리
public class Main {
    static int N, M, K;
    static int startIndex;
    static long[] indexedTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // depth 구하기
        int depth = (int) Math.ceil(Math.log(N) / Math.log(2));

        // 인덱스 트리 값 넣기
        startIndex = (int) Math.pow(2, depth);
        int treeSize = (int) Math.pow(2, depth + 1) - 1;
        indexedTree = new long[treeSize + 1];

        // 숫자 입력 받음
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            indexedTree[startIndex + i] = num;
        }

        // 인덱스 트리 완성하기
        for (int i = startIndex; i < startIndex + N; i++) {
            int index = i; // 8
            while (index > 1) {
                indexedTree[index / 2] += indexedTree[i];
                index /= 2;
            }
        }

        // 쿼리문 = 값 변경 OR 누적합
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // 값 변경
            if (a == 1) {
                changeValue(b, c);
            }
            // 누적 합
            else if (a == 2) {
                long result = query(b, (int)c);
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static long query(int start, int end) {
        start = startIndex + start - 1;
        end = startIndex + end - 1;

        long result = 0;
        while (start <= end) {
            if (start % 2 == 1) {
                result += indexedTree[start];
            }
            if (end % 2 == 0) {
                result += indexedTree[end];
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return result;
    }

    public static void changeValue(int order, long value) {
        int index = startIndex + order - 1;
        long diff = value - indexedTree[index];
        indexedTree[index] = value;

        while (index > 1) {
            indexedTree[index / 2] += diff;
            index /= 2;
        }
    }
}
