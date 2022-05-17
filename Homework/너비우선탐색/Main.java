package Homework.너비우선탐색;

// 백준 1260번과 거의 유사
// 1. 인접리스트 구현하는 것에서 조금 해멨다
// 2. 무향 그래프라서  s->d , d->s 2개의 연결이 필요하다
// 3. 정렬하는 코드가 graph 클래스에 있는 것이 더 나아 보인다
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,V;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());
        Graph g=new Graph(N);
        isVisited=new boolean[N+1];
        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            g.put(s,d);
            g.put(d,s);
        }
        // 작은 거 부터 출력하기 위해 인접리스트 정렬
        for(int i=1; i<N+1; i++){
            ArrayList<Integer> arr=g.getList(i);
            Collections.sort(arr);
        }

        // bfs
        // 초기화
        Queue<Integer> q=new LinkedList<>();
        q.add(V);
        isVisited[V]=true;
        while(!q.isEmpty()){
            // 1. 큐에서 꺼내기
            int x=q.poll();
            // 2. 목적지인가?
            sb.append(x).append(" ");
            // 3. 연결된 곳을 순회
            for(int value: g.getList(x)){
                //      4. 갈 수 있는가?
                if(!isVisited[value]) {
                    //          5. 체크인
                    isVisited[value] = true;
                    //          6. 큐에 넣기
                    q.add(value);
                }
            }
        }
        System.out.print(sb);

    }
}
class Graph{
    int v;
    ArrayList<ArrayList<Integer>> adjacencyList;
    public Graph(int v){
        this.v=v;
        adjacencyList=new ArrayList<>(v+1);
        for(int i=0; i<v+1; i++){
            adjacencyList.add(new ArrayList<>());
        }
    }
    public void put(int start, int destination){
        adjacencyList.get(start).add(destination);
    }
    public ArrayList<Integer> getList(int index){
        return adjacencyList.get(index);
    }
}
