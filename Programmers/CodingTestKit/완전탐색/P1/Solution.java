package Programmers.CodingTestKit.완전탐색.P1;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] answers={1,1,1,1,1,3,3,3,3,3};
        int[] solve=solution(answers);
    }
    public static int[] solution(int[] answers) {
        // 각 학생 점수 구하기
        int score1=0,score2=0,score3=0;
        HashMap<Integer,Integer> map1=new HashMap<>();
        map1.put(0,1); map1.put(1,2); map1.put(2,3); map1.put(3,4); map1.put(4,5);
        HashMap<Integer,Integer> map2=new HashMap<>();
        map2.put(0,2); map2.put(2,2); map2.put(4,2); map2.put(6,2);
        map2.put(1,1); map2.put(3,3); map2.put(5,4); map2.put(7,5);
        HashMap<Integer,Integer> map3=new HashMap<>();
        map3.put(0,3); map3.put(2,1); map3.put(4,2); map3.put(6,4); map3.put(8,5);
        map3.put(1,3); map3.put(3,1); map3.put(5,2); map3.put(7,4); map3.put(9,5);
        for(int i=0; i<answers.length; i++){
            // 1번 수포자 점수
            int i1=i%5;
            if(answers[i]==map1.get(i1)) score1++;
            // 2번 수포자 점수
            int i2=i%8;
            if(answers[i]==map2.get(i2)) score2++;
            // 3번 수포자 점수
            int i3=i%10;
            if(answers[i]==map2.get(i3)) score3++;
        }
        // TreeMap 활용해서 정렬
        TreeMap<Integer,Integer> m=new TreeMap<>();
        m.put(1,score1);  m.put(2,score2);  m.put(3,score3);
        int max=0;
        for(Integer student:m.keySet()){
            max=Math.max(m.get(student),max);
        }
        ArrayList<Integer> arr=new ArrayList<>();
        for(Integer student:m.keySet()){
            if(m.get(student)==max) arr.add(student);
        }
        System.out.println(arr.size());
        int[] answer = new int[arr.size()];
        for(int i=0; i<arr.size(); i++){
            answer[i]=arr.get(i);
        }
        return answer;
    }
}
