package Programmers.CodingTestKit.그래프.P1;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        int[][] edge={{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
        int x=solution(6,edge);
        System.out.println(x);
    }
    public static int solution(int n, int[][] edge) {
        // graph 초기화
        ArrayList<Integer>[] graph=new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            graph[i]=new ArrayList<>();
        }
        boolean[] isVisited=new boolean[n+1];
        for(int[] e:edge){
            int head=e[0];
            int tail=e[1];
            graph[head].add(tail);
            graph[tail].add(head);
        }

        // 우선순위 큐를 활용한 다익스트라 알고리즘
        // 초기화
        int[] shortestDistance=new int[n+1];
        for(int i=0; i<n+1; i++){
            shortestDistance[i]=20000;
        }
        shortestDistance[1]=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.add(1);
        while(!pq.isEmpty()){
            int curV=pq.poll();
            isVisited[curV]=true;
            for(int nextV:graph[curV]){
                if(!isVisited[nextV]){
                    if(shortestDistance[nextV]>shortestDistance[curV]+1){
                        shortestDistance[nextV]=shortestDistance[curV]+1;
                        pq.add(nextV);
                    }
                }
            }
        }
        // 배열에서 max 값 구하고 max 값 개수 count
        int max=0;
        for(int i=1; i<n+1; i++){
            max=Math.max(max,shortestDistance[i]);
        }
        int count=0;
        for(int i=1; i<n+1; i++){
            if(shortestDistance[i]==max) count++;
        }
        return count;
    }
}
