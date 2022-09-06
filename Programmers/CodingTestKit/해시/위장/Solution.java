package Programmers.CodingTestKit.해시.위장;
// 1차 풀이 : 11:30~12:10 -> 1번 tc 메모리 초과
// 2차 풀이 : ArrayList에 담지말고 바로 계산하는 쪽으로 -> 시간 초과
// 3
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int[] number;
    static boolean[] isVisited;
    static int[] comb;
    static int[] counts; // 의상 종류에 따른 개수 들어있는 배열
    static Map<String, Integer> m = new HashMap<>();
    static int answer = 0;
    public int solution(String[][] clothes) {
        // map 초기화
        for (int i = 0; i < clothes.length; i++) {
            m.put(clothes[i][1], 0);
        }
        // map 생성
        for (int i = 0; i < clothes.length; i++) {
            m.put(clothes[i][1], m.get(clothes[i][1]) + 1);
        }
        // int[] 배열로 넘겨두기
        counts = new int[m.size()];
        int index=0;
        for (String key : m.keySet()) {
            counts[index++] = m.get(key);
        }

        // 조합 생성을 위한 초기화
        isVisited = new boolean[counts.length + 1];
        number = new int[counts.length + 1];
        for (int i = 0; i < number.length; i++) {
            number[i] = i;
        }
        // 1개~N개 의 조합을 생성하도록
        for (int i = 1; i <= counts.length; i++) {
            comb = new int[i + 1];
            back(1, i);
        }
        return answer;
    }

    public static void back(int depth,int target) {
        if (depth == target + 1) {
            int tempAnswer=1;
            for (int i = 1; i < comb.length; i++) {
                tempAnswer *= counts[comb[i] - 1];
            }
            answer += tempAnswer;
            return;
        }
        for (int i = 1; i < number.length; i++) {
            if (!isVisited[i] && comb[depth - 1] < number[i]) {
                comb[depth] = number[i];
                isVisited[i] = true;
                back(depth + 1, target);
                isVisited[i] = false;
            }
        }
    }
}
