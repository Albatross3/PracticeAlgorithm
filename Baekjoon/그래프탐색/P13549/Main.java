package Baekjoon.그래프탐색.P13549;

// 백준 - 숨박꼭질
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[] isVisited = new boolean[100000 + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // bfs
        Dot start = new Dot(N, 0);
        PriorityQueue<Dot> queue = new PriorityQueue<>(((o1, o2) -> o1.time - o2.time));
        queue.add(start);
        while (!queue.isEmpty()) {
            // 1. 큐에서 뺀다
            Dot dot = queue.poll();
            // 2. 목적지 인가?
            if (dot.pos == K) {
                System.out.println(dot.time);
                break;
            }
            // 3. 갈 수 있는 곳 순회 -> X-1, X+1, 2*X
            // 4. 갈 수 있는가? 0 - 100000
            // 5. 간다
            // 6. 큐에 넣기
            if (dot.pos * 2 <= 100000 && !isVisited[dot.pos*2]) {
                isVisited[dot.pos * 2] = true;
                queue.add(new Dot(dot.pos * 2, dot.time));
            }
            if (dot.pos - 1 >= 0 && !isVisited[dot.pos-1]) {
                isVisited[dot.pos-1] = true;
                queue.add(new Dot(dot.pos - 1, dot.time + 1));
            }
            if (dot.pos + 1 <= 100000 && !isVisited[dot.pos+1]) {
                isVisited[dot.pos + 1] = true;
                queue.add(new Dot(dot.pos + 1, dot.time + 1));
            }


        }

    }
}
class Dot{
    int pos;
    int time;

    public Dot(int pos, int time) {
        this.pos = pos;
        this.time = time;
    }
}


