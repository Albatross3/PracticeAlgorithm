package Baekjoon.그래프탐색.P1697;
// 직선 상에서도 bfs 가 가능함을 알려주는 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] dp = new int[100000 + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N==K) System.out.println(0);
        else System.out.println(bfs(N));
    }

    public static int bfs(int loc) {
        Queue<Integer> q = new LinkedList<>();
        q.add(loc);
        dp[loc] = 0;

        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            int temp = q.poll();
            // 2. 목적지인가?
            if(temp==K) return dp[temp];
            // 3. 연결된 곳 순회
            int next;
            for (int i = 0; i < 3; i++) {
                if(i==0) next = temp + 1;
                else if(i==1) next = temp -1;
                else next = temp * 2;
                // 4. 갈 수 있는가? -> 수직선 안에 존재 & 가지 않았던 곳
                if (next >= 0 && next <= dp.length - 1 && dp[next] == 0) {
                    // 5. 간다
                    dp[next] = dp[temp] + 1;
                    // 6. 큐에 넣기
                    q.add(next);
                }
            }
        }
        return -1;
    }
}
