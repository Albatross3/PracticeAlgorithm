package Programmers.KAKAO_INTERN_2019.L2;

import java.util.*;

// 8:35 시작
// 8:43 이해 완료
// 설계
// 9:11 완료
// 오류발견 후 9:44 오류 수정
// refactoring 필요
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
        System.out.println(Arrays.toString(array));
    }
    public int[] solution(String s) {
        Map<Integer, Set<Integer>> m = new TreeMap<>();
        s=s.substring(1,s.length()-1);
        //String[] arr = s.split(","); // 여기서 잘못 나누게 됨, 내 의도대로 안들어감
        s=s.replaceAll("},\\{","}|{");
        String[] arr = s.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set = new LinkedHashSet<>();
            String temp = arr[i].substring(1, arr[i].length() - 1);
            String[] tempArr = temp.split(",");
            for (int j = 0; j < tempArr.length; j++) {
                set.add(Integer.parseInt(tempArr[j]));
            }
            m.put(set.size(), set);
        }

        Set<Integer> tempAnswer = new LinkedHashSet<>();
        for (int key : m.keySet()) {
            Set<Integer> set = m.get(key);
            set.removeAll(tempAnswer);
            tempAnswer.addAll(set);
        }

        int[] answer = new int[tempAnswer.size()];
        int index=0;
        for (int x : tempAnswer) {
            answer[index] = x;
            index++;
        }
        return answer;
    }
}
