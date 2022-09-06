package Programmers.CodingTestKit.완전탐색.모의고사;

// int[] 로 반환하는 문제 : 보통 ArrayList 만들고 int[] 로 변환하게 됨
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int[] answers={1,1,1,1,1,3,3,3,3,3};
        int[] solve=solution(answers);
    }
    public static int[] solution(int[] answers) {
        // 각 학생 점수 구하기
        int[] score=new int[3];
        int[] student1={1,2,3,4,5};
        int[] student2={2,1,2,3,2,4,2,5};
        int[] student3={3,3,1,1,2,2,4,4,5,5};
        for(int i=0; i<answers.length; i++){
            // 1번 수포자 점수
            if(answers[i]==student1[i%5]) score[0]++;
            // 2번 수포자 점수
            if(answers[i]==student2[i%8]) score[1]++;
            // 3번 수포자 점수
            if(answers[i]==student3[i%10]) score[2]++;
        }
        // 3개 중에서 최댓값 찾을 때 쓰는 방법
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        ArrayList<Integer> arr = new ArrayList<>();
        if(score[0]==maxScore) arr.add(1);
        if(score[1]==maxScore) arr.add(2);
        if(score[2]==maxScore) arr.add(3);

        int[] answer=new int[arr.size()];
        for(int i=0; i<arr.size(); i++){
            answer[i]= arr.get(i);
        }
        return answer;
    }
}
