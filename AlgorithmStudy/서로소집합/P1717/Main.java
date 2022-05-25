package AlgorithmStudy.서로소집합.P1717;

// 백준 - 집합의 표현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] parent;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        parent=new int[n+1];
        for(int i=0; i<n+1; i++)
            parent[i]=i;
        for(int i=0; i<m; i++){
            st=new StringTokenizer(br.readLine());
            int operation=Integer.parseInt(st.nextToken());
            int value1=Integer.parseInt(st.nextToken());
            int value2=Integer.parseInt(st.nextToken());
            // Union
            if(operation==0)
                union(value1,value2);
            // Find
            else if(operation==1)
                isSameSet(value1,value2);
        }
        System.out.println(sb);
    }
    // a가 속한 트리의 root element 가리킴
    public static int find(int a){
        if(parent[a]==a) return a;
        else return find(parent[a]);
    }
    // i가 속한 tree 와 j가 속한 트리를 합치기
    // i쪽에 몰아주기
    public static void union(int i,int j){
        int root_i=find(i);
        int root_j=find(j);
        parent[root_j]=root_i;
    }
    public static void isSameSet(int i,int j){
        int root_i=find(i);
        int root_j=find(j);
        if(root_i==root_j) sb.append("YES").append("\n");
        else sb.append("NO").append("\n");
    }

}
