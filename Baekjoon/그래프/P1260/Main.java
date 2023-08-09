package Baekjoon.그래프.P1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 그래프 - 인접리스트로 표현
// 그래프 - DFS 와 BFS
public class Main {
    static int V,E,S;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());

        // 인접 리스트로 그래프 구현
        graph=new ArrayList<>(V+1);
        for (int i=0; i<V+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // 그래프 정렬 (숫자 작은 순서로 출력 위함)
        for(int i=1; i<V+1; i++){
            ArrayList<Integer> temp=graph.get(i);
            Collections.sort(temp);
        }

//        // 그래프 출력
//        for(int i=1; i<V+1; i++){
//            System.out.print(i+" -> ");
//            for(int j=0; j<graph.get(i).size(); j++){
//                System.out.print(graph.get(i).get(j)+" ");
//            }
//            System.out.println();
//        }

        // dfs 실행
        visited=new boolean[V+1];
        dfs(S);

        System.out.println();
        // bfs 실행
        visited=new boolean[V+1];
        bfs(S);

    }

    static void dfs(int node){
        // 1. 체크인
        visited[node]=true;
        // 2. 목적지인가?
        System.out.print(node+" ");
        // 3. 연결된 곳을 순회
        for(int n: graph.get(node)){
            //      4.갈 수 있는가?
            if(!visited[n])
                //          5.간다
                dfs(n);
        }
        // 6. 체크 아웃
    }
    static void bfs(int node){
        // 0. 시작
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(node);
        visited[node]=true;

        // 1. 큐에서 꺼내옴
        while(queue.size()!=0){
            int n=queue.poll();
            // 2. 목적지 인가?
            System.out.print(n+" ");
            // 3. 연결된 곳을 순회
            for(int adj: graph.get(n)){
                //      4. 갈 수 있는가?
                if(!visited[adj]){
                    //          5. 체크인
                    visited[adj]=true;
                    //          6. 큐에 넣음
                    queue.offer(adj);
                }
            }
        }

    }
}
