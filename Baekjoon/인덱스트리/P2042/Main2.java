package Baekjoon.인덱스트리.P2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Top-down 풀이 - 세그먼트 트리
public class Main2 {
    static int N, M, K;
    static long[] segmentTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int treeSize = (int) Math.pow(2, height + 1);

        segmentTree = new long[treeSize];
        for (int i = 0; i < N; i++) {
            segmentTree[startIndex + i] = Long.parseLong(br.readLine());
        }

        // segmentTree 생성
        makeTree(1, 1, startIndex);

        // query -> update or part sum
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, 1, startIndex, b, c - segmentTree[startIndex + b - 1]);
            } else if (a == 2) {
                sb.append(query(1, 1, startIndex, b, (int) c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static long makeTree(int node, int start, int end) {
        if (start == end) {
            return segmentTree[node];
        } else {
            int mid = (start + end) / 2;
            return segmentTree[node] = makeTree(node * 2, start, mid) + makeTree(node * 2 + 1, mid+1, end);
        }
    }

    public static void update(int node, int left, int right, int index, long diff) {
        if(node >= segmentTree.length) return;
        if (left <= index && index <= right) {
            segmentTree[node] += diff;
            int mid = (left + right) / 2;
            update(node * 2, left, mid, index, diff);
            update(node * 2 + 1, mid + 1, right, index, diff);
        }
    }

    public static long query(int node, int left, int right, int qLeft, int qRight) {
        if (qRight < left || right < qLeft) return 0;
        // 구하려는 구간안에 노드 구간이 있는 경우
        else if (qLeft <= left && right <= qRight) return segmentTree[node];
        else {
            int mid = (left + right) / 2;
            return query(node * 2, left, mid, qLeft, qRight) + query(node * 2 + 1, mid + 1, right, qLeft, qRight);
        }
    }
}
