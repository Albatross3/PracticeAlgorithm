package Programmers.CodingTestKit.해시.위장;

// getOrDefault 라는 Map 의 메소드를 이용하면 해당 key 값의 해당하는 값을 가져오거나 default 값으로 설정한다
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int solution(String[][] clothes) {
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            m.put(clothes[i][1], m.getOrDefault(clothes[i][1], 0)+1);
        }
        int answer = 1;
        for (String key : m.keySet()) {
            answer *= (m.get(key)+1);
        }
        return answer - 1;
    }
}
