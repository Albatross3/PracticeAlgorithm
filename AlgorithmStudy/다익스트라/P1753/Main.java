package AlgorithmStudy.다익스트라.P1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int K;

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] dist; // 출발점으로부터 1~V 까지의 최단거리
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        // 그래프 생성
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dist = new int[V + 1];
        for (int i = 1; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[K] = 0;
        isVisited = new boolean[V + 1];

        dijkstra(K);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            }
            else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
    }
    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (isVisited[current.end]) continue;
            else isVisited[current.end] = true;

            for (Node next : graph.get(current.end)) {
                if (dist[current.end] + next.cost < dist[next.end]) {
                    dist[next.end] = dist[current.end] + next.cost;
                }
                pq.add(new Node(next.end, dist[next.end]));

            }
        }
    }
}
class Node implements Comparable<Node>{
    int end;
    int cost;

    public Node(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}
