package DevMatching.P1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] answer;
    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Math.max(1,2);
    }
}
//import java.util.*;
//class Solution {
//    public int[][] solution(int[][] dist) {
//        int length=dist.length;
//        int I=0;
//        int J=0;
//        int max=0;
//        for(int i=1; i<length; i++){
//            for(int j=0; j<i; j++){
//                if(dist[i][j]>max){
//                    max=dist[i][j];
//                    I=i;
//                    J=j;
//                }
//            }
//        }
//
//        Map<Integer,Integer> m=new HashMap<>();
//        for(int i=0; i<length; i++){
//            m.put(i,dist[I][i]);
//        }
//        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(m.entrySet());
//        entryList.sort(Map.Entry.comparingByValue());
//        int[] answer1=new int[length];
//        int x=0;
//        for(Map.Entry<Integer, Integer> entry : entryList){
//            answer1[x]=entry.getKey();
//            x++;
//        }
//        int k=0;
//        int[] answer2=new int[length];
//        for(int i=length-1; i>=0; i--){
//            answer2[k]=answer1[i];
//            k++;
//        }
//        int[][] answer=new int[2][length];
//        if(answer1[0]>answer2[0]) {
//            answer[0]=answer2;
//            answer[1]=answer1;
//        }
//        else {
//            answer[0]=answer1;
//            answer[1]=answer2;
//        }
//        return answer;
//    }
//}
