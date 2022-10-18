package LeetCode.WordLadder;

import java.util.*;

public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // wordList set 로 변환
        Set<String> set = new HashSet<>(wordList);
        // endWord 가 list 에 없으면 0 return
        if (!set.contains(endWord)) return 0;

        // BFS
        // 방문한 배열은 Set 로 표시 -> index 접근보다 더 빠른 방식
        int change = 0;
        Set<String> isVisited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        isVisited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            change++;
            // 같은 level 에서 같은 change 값을 갖게 하기 위함
            for (int S = 0; S < size; S++) {
                // 1. 큐에서 꺼내기
                String s = queue.poll();
                // 2. 목적지인가?
                if (s.equals(endWord)) return change;
                // 3. 연결된 곳 순회
                char[] arr = s.toCharArray();
                for (int l = 0; l < s.length() ; l++) {
                    char c = arr[l];
                    for (int i = 0; i < 26; i++) {
                        arr[l] = (char) ('a' + i);
                        if (arr[l] == c) continue;
                        String temp = new String(arr);
                        // 4. 갈 수 있는가? -> wordList 안에 존재 & 방문하지 않은 문자열
                        if (set.contains(temp) && !isVisited.contains(temp)) {
                            // 5. 간다
                            isVisited.add(temp);
                            // 6. 큐에 넣기
                            queue.add(temp);
                        }
                    }
                    // 원래 문자 복구
                    arr[l] = c;
                }
            }
        }
        return 0;
    }
}