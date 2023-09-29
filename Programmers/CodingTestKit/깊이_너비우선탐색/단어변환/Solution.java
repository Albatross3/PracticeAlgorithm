package Programmers.CodingTestKit.깊이_너비우선탐색.단어변환;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int answer = Integer.MAX_VALUE;
    static boolean[] isVisited;
    public int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];
        return bfs(begin, target, words);
    }

    public static int bfs(String begin, String target, String[] words) {
        Queue<Word> q = new LinkedList<>();
        boolean[] isVisited = new boolean[words.length];
        q.add(new Word(begin, 0));

        int count = 0;
        while (!q.isEmpty()) {
            Word cur = q.poll();
            if (cur.word.equals(target)) {
                count = cur.count;
                break;
            }
            for (int i = 0; i < words.length; i++) {
                int matchLength = 0;
                for (int j = 0; j < begin.length(); j++) {
                    if (cur.word.charAt(j) == words[i].charAt(j)) {
                        matchLength++;
                    }
                }
                if (!isVisited[i] && matchLength == begin.length() - 1) {
                    isVisited[i] = true;
                    q.add(new Word(words[i], cur.count + 1));
                }
            }
        }
        return count;
    }

    static class Word {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
