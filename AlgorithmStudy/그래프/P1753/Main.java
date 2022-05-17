package AlgorithmStudy.그래프.P1753;

// 우선순위 큐를 이용하는 듯 
// 우선순위를 이용하지 않고서 풀었는데 어떤 지점에서 틀린지 알고 싶음
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V,E;
    static int start;
    static ArrayList<ArrayList<Node>> graph=new ArrayList<>();
    static boolean[] isVisited;
    static int[] pathDistance;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        start=Integer.parseInt(br.readLine());

        isVisited=new boolean[V+1];
        pathDistance=new int[V+1];
        for(int i=1; i<=V; i++)
            pathDistance[i]=1000000;
        // graph 초기화 ( ArrayList 안에 ArrayList<Node> 정점 수만큼 만들기 )
        for(int i=0; i<=E; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<E; i++){
            st=new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            int index=isExist(graph,from,to);
            if(index!=-1){
                if(graph.get(from).get(index).weight > weight ) graph.get(from).get(index).weight=weight;
            }
            else graph.get(from).add(new Node(to,weight));
        }
        dijkstra();
        System.out.println(sb);

    }
    public static void dijkstra(){
        // 초기화
        isVisited[start]=true;
        for(Node n:graph.get(start)){
            pathDistance[n.to]=n.weight;
        }
        // UPDATE
        for(int c=1; c<=V-1; c++){
            // 최소 경로 찾기
            int temp=10;
            int index=0;
            for(int i=1; i<=V; i++){
                if(!isVisited[i] && pathDistance[i]>0) {
                    if(pathDistance[i]<=temp){
                        temp=pathDistance[i];
                        index=i;
                    }
                }
            }
            isVisited[index]=true;

            // pathDistance UPDATE
            for(int i=1; i<=V; i++){
                if(!isVisited[i]){
                    if(getWeight(graph,index,i)!=-1)
                        pathDistance[i]=Integer.min(pathDistance[i],pathDistance[index]+getWeight(graph,index,i));
                }
            }

        }
        // pathDistance 출력 부분
        for(int i=1; i<=V; i++){
            if(i!=start){
                if(pathDistance[i]!=1000000) sb.append(pathDistance[i]).append("\n");
                else sb.append("INF").append("\n");
            }
            else sb.append(0).append("\n");
        }

    }
    public static int getWeight(ArrayList<ArrayList<Node>> g,int from,int to){
        for(Node n:g.get(from)){
            if(n.to==to) return n.weight;
        }
        // 존재하지 않는 경우
        return -1;
    }
    public static int isExist(ArrayList<ArrayList<Node>> g,int from,int to){
        int index=0;
        for(Node n:g.get(from)){
            if(n.to==to) return index;
            index++;
        }
        return -1;
    }
}
class Node{
    int to;
    int weight;
    public Node(int to,int weight){
        this.to=to;
        this.weight=weight;
    }
}
