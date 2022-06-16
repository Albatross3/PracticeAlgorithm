package Programmers.CodingTestKit.완전탐색.P1;

// int[] 로 반환하는 문제 : 보통 ArrayList 만들고 int[] 로 변환하게 됨
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] answers={1,1,1,1,1,3,3,3,3,3};
        int[] solve=solution(answers);
    }
    public static int[] solution(int[] answers) {
        // 각 학생 점수 구하기
        int score1=0,score2=0,score3=0;

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
