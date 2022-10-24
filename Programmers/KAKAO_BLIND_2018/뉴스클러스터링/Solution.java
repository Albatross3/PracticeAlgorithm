package Programmers.KAKAO_BLIND_2018.뉴스클러스터링;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Solution {

    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        // 1. 모두 소문자로 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 2. 유효한 글자 쌍(영문자 2개)을 추출해서 Map 에 저장
        String pattern = "[a-z]{2}";
        for (int i = 0; i < str1.length() - 1; i++) {
            String temp = str1.substring(i, i + 2);
            if (Pattern.matches(pattern, temp)) {
                map1.put(temp, map1.getOrDefault(temp, 0) + 1);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            String temp = str2.substring(i, i + 2);
            if (Pattern.matches(pattern, temp)) {
                map2.put(temp, map2.getOrDefault(temp, 0) + 1);
            }
        }
        if(map1.size()==0 && map2.size()==0) return 65536;

        // 3. 교집합 개수, 합집합 개수 구하기
        int sumInter = 0;
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                sumInter += Math.min(map1.get(key), map2.get(key));
            }
        }
        int sumUnion = 0;
        Set<String> unionSet = new HashSet<>();
        // 먼저 map1 부터
        for (String key : map1.keySet()) {
            unionSet.add(key);
            if (map2.containsKey(key)) {
                sumUnion += Math.max(map1.get(key), map2.get(key));
            } else {
                sumUnion += map1.get(key);
            }
        }
        // 그 다음 map2
        for (String key : map2.keySet()) {
            if (!unionSet.contains(key)) {
                unionSet.add(key);
                sumUnion += map2.get(key);
            }
        }

        double answer = (double)sumInter / sumUnion * 65536;
        return (int)answer;
    }
}
