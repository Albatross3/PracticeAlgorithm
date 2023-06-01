package Programmers.코딩테스트입문.등수매기기;

import java.util.*;

// indexOf 함수의 활용 -> 일치하는 값의 가장 앞의 index를 가져올 수 있다
public class Solution {
    public int[] solution(int[][] score) {
        int[] answer = new int[score.length];
        List<Integer> list = new ArrayList<>();
        for(int[] s : score) {
            list.add(s[0]+s[1]);
        }
        Collections.sort(list, Collections.reverseOrder());

        for(int i=0; i<score.length; i++) {
            answer[i] = list.indexOf(score[i][0] + score[i][1]) + 1;
        }
        return answer;
    }
}
