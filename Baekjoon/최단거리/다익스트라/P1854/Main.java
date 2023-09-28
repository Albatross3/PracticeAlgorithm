package Baekjoon.최단거리.다익스트라.P1854;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Integer>[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        dist = new PriorityQueue[n+1];
        for (int i = 1; i < dist.length; i++) {
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        dist[1].add(0);

        dijkstra(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dist.length; i++) {
            if(dist[i].size()==k) sb.append(dist[i].peek()).append("\n");
            else sb.append(-1).append("\n");
        }
        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : graph[current.vertex]) {
                if (dist[next.vertex].size() < k) {
                    dist[next.vertex].add(current.cost + next.cost);
                    pq.add(new Node(next.vertex, current.cost + next.cost));
                } else if (dist[next.vertex].peek() > current.cost + next.cost) {
                    dist[next.vertex].poll();
                    dist[next.vertex].add(current.cost + next.cost);
                    pq.add(new Node(next.vertex, current.cost + next.cost));
                }
            }
        }
    }

    static class Node {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
