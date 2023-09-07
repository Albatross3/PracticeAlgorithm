package Programmers.KAKAO_BLIND_2018.압축;

import java.util.*;
public class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dictionary = new HashMap<>();
        for(int i=0; i<26; i++) {
            dictionary.put(String.valueOf((char)('A'+i)), i+1);
        }

        List<Integer> list = new ArrayList<>();
        int index = 0;
        int nextIndex = 27;
        String currentInput = "";
        while(true) {
            // 1. 현재 사전에서 찾을 수 있는 최대 길이 w 찾기
            while(index != msg.length() && dictionary.get(currentInput + msg.charAt(index)) != null) {
                currentInput += msg.charAt(index);
                index++;
            }

            // 2. w 의 색인 번호 출력 및 현재 입력에서 w 제거
            list.add(dictionary.get(currentInput));
            if(index ==  msg.length()) break;

            // 3. 다음 index 위치의 c 에서 w+c 를 사전에 등록
            String newWord = currentInput + msg.charAt(index);
            dictionary.put(newWord, nextIndex++);

            currentInput = String.valueOf(msg.charAt(index++));
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
