package Baekjoon.그래프탐색.너비우선탐색.P24444;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 너비 우선 탐색 기본 문제 : bfs
// 그래프를 코드로 나타내는 방법 : 무방향 그래프
// 1. ArrayList<ArrayList<>>
// 2. ArrayList<>[]
public class Main {
    static int N,M,R;
    static boolean[] isVisited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 그래프 생성
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        // 그래프 정렬
        for (ArrayList<Integer> arr : graph) {
            Collections.sort(arr);
        }

        isVisited = new boolean[N+1];
        q.add(R);
        isVisited[R]=true;
        result = new int[N + 1];
        bfs();

        for (int i = 1; i < result.length; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs() {
        int order=1;
        while(!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            int node = q.poll();
            // 2. 목적지인가? -> 그냥 출력
            result[node]=order++;
            // 3. 연결된 곳 순회
            for (int n : graph.get(node)) {
                // 4. 갈 수 있는가?
                if (!isVisited[n]) {
                    // 5. 간다
                    isVisited[n] = true;
                    // 6. 큐에 넣기
                    q.add(n);
                }
            }
        }

    }
}
