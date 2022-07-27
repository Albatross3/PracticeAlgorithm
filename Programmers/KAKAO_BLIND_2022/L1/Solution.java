package Programmers.KAKAO_BLIND_2022.L1;

// 신고 결과 받기
// Map 과 Set 은 순서가 보장 안된다는 것을 알게됨

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Map<String, HashSet<String>> m = new HashMap<>();
        m.put("muzzi",new HashSet<>());
        m.put("dragon",new HashSet<>());
        m.put("alpha",new HashSet<>());
        m.get("muzzi").add("frodo");
        m.get("muzzi").add("leesin");
        m.get("muzzi").add("leesin");
        System.out.println(m);
        Set<String> a = new LinkedHashSet<>();
        Set<String> b = new LinkedHashSet<>();

        a.add("neo");
        a.add("leesin");


        b.add("leesin");
        b.add("yasuo");

        a.retainAll(b);
        System.out.println(a);
        System.out.println(b);
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> m = new LinkedHashMap<>();
        Map<String, Integer> countReported = new LinkedHashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            m.put(id_list[i], new LinkedHashSet<>());
            countReported.put(id_list[i], 0);
        }
        for (int i = 0; i < report.length; i++) {
            st = new StringTokenizer(report[i]);
            m.get(st.nextToken()).add(st.nextToken());
        }

        // 신고당한 횟수 count
        for (String key : m.keySet()) {
            for(String reported:m.get(key)){
                countReported.put(reported,countReported.get(reported)+1);
            }
        }

        HashSet<String> forbiddenId = new LinkedHashSet<>();
        for (String id : countReported.keySet()) {
            if (countReported.get(id) >= k) {
                forbiddenId.add(id);
            }
        }

        // 금지된 id와 겹치는 개수 세기
        int i=0;
        for (String key : m.keySet()) {
            m.get(key).retainAll(forbiddenId);
            answer[i]=m.get(key).size();
            ++i;
        }

        return answer;
    }
}
