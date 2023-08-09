package Baekjoon.자료구조.힙.P1202;

// PriorityQueue 활용문제
// Greedy + 정렬 + PriorityQueue
// 1. 배낭 무게를 정렬
// 2. 정렬된 배낭 무게 보다 같거나 작은 보석 중 최대의 가치를 가지는 보석만 선택
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,K;
    static int[][] jewelry;
    static int[] bags;
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewelry = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewelry[i][0] = Integer.parseInt(st.nextToken());
            jewelry[i][1] = Integer.parseInt(st.nextToken());
        }
        bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 1. 보석들에 대한 정렬
        // 2. 배낭 정렬
        Arrays.sort(jewelry, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        Arrays.sort(bags);

        // 정렬된 배낭 무게보다 작거나 같은 보석들 중 최대 가치 선택
        PriorityQueue<int[] > pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];  // 가치가 클수록 우선순위 앞으로 옴
            }
        });

        int index=0;
        for (int i = 0; i < K; i++) {
            while (index<N) {
                if(bags[i] < jewelry[index][0]) break;
                pq.add(jewelry[index++]);
            }
            if(!pq.isEmpty()) answer += pq.poll()[1];
        }
        System.out.println(answer);

    }
}
