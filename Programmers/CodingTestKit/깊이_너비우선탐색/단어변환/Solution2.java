package Programmers.CodingTestKit.깊이_너비우선탐색.단어변환;

public class Solution2 {
    static int answer = Integer.MAX_VALUE;
    static boolean[] isVisited;
    public int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];
        dfs(begin, target, words, 0);
        if(answer == Integer.MAX_VALUE) return 0;
        else return answer;
    }

    public static void dfs(String begin, String target, String[] words, int count) {
        if (begin.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }
        for (int i = 0; i < isVisited.length; i++) {
            int matchLength = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    matchLength++;
                }
            }
            if (!isVisited[i] && matchLength == begin.length() - 1) {
                isVisited[i] = true;
                dfs(words[i], target, words, count + 1);
                isVisited[i] = false;
            }
        }
    }
}
