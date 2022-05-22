package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2 {
    static int N,M,R;
    static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
    static boolean[] visited;
    static int[] visitedOrder;
    static int order=1;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }
        visited=new boolean[N+1];
        visitedOrder=new int[N+1];
        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // 오름차순 정렬
        for(int i=0; i<=N; i++){
            Collections.sort(graph.get(i));
        }
        // dfs
        dfs(R);
        for(int i=1; i<=N; i++) sb.append(visitedOrder[i]).append("\n");
        System.out.println(sb);
    }
    public static void dfs(int start){
        // 1. 체크인
        visited[start]=true;
        // 2. 목적지인가?
        visitedOrder[start]=order++;
        // 3. 연결된 곳 순회
        for(int v:graph.get(start)){
            //      4. 갈 수 있는가?
            if(!visited[v]) {
                //          5. 간다
                dfs(v);
            }
        }

        // 6. 체크아웃
    }
}
