package Homework.다익스트라;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static int[][] graph;
    static boolean[] isVisited;
    static int[] shortestDistance;
    static ArrayList<Integer> route=new ArrayList<>();
    static  StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V=Integer.parseInt(br.readLine());
        graph=new int[V+1][V+1];
        isVisited=new boolean[V+1];
        shortestDistance=new int[V+1];

        for(int i=1; i<=V; i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1; j<=V; j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        // 0으로 들어온 값들 변경
        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                if(graph[i][j]==0) graph[i][j]=999;
            }
        }
        dijkstra();
        System.out.println(sb);

    }
    public static void dijkstra(){

        // 초기화 단계
        route.add(1);
        isVisited[1]=true;
        for(int i=2; i<=V; i++){
            shortestDistance[i]=graph[1][i];
        }
        // 초기 출력 부분
        sb.append("{").append(1).append("}").append(",");
        for(int v=2; v<=V; v++){
            if(v==V) sb.append(shortestDistance[v]);
            else sb.append(shortestDistance[v]).append(",");
        }
        sb.append("\n");
        // UPDATE 단계
        for(int i=1; i<=V-1; i++){
            // 최소 경로 길이 결정
            int temp=999;
            int index=0;
            for(int x=2; x<=V; x++){
                if(!isVisited[x]) {
                    if(shortestDistance[x]<temp) {
                        index=x;
                        temp=shortestDistance[x];
                    }
                }
            }
            route.add(index);
            isVisited[index]=true;

            // shortestPath update
            for(int v=2; v<=V; v++){
                if(!isVisited[v]) {
                    shortestDistance[v]=Integer.min(shortestDistance[v], shortestDistance[index]+graph[index][v]);
                }
            }

            // UPDATE 후 출력 부분
            sb.append("{");
            for(int s=0; s< route.size(); s++){
                if(s==route.size()-1) sb.append(route.get(s));
                else sb.append(route.get(s)).append(",");
            }
            sb.append("}").append(",");
            for(int v=2; v<=V; v++){
                if(v==V) sb.append(shortestDistance[v]);
                else sb.append(shortestDistance[v]).append(",");
            }
            sb.append("\n");
        }
    }
}
