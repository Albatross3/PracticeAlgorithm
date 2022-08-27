package Programmers.KAKAO_INTERN_2019.L2;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
    }
    public int[] solution(String s) {
        String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]"," ").trim().split(" , ");
        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());
        int[] answer = new int[arr.length];
        Set<String> tempSet = new HashSet<>();
        int index=0;
        for (String set : arr) {
            for (String e : set.split(",")) {
                if(tempSet.add(e)) // add
                    answer[index++] = Integer.parseInt(e);
            }
        }
        return answer;
    }
}
