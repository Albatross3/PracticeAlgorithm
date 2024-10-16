package LeetCode.WordBreak;

// 리트코드 139번
// DP 활용하는 문제인데 다시 풀어봐야 함
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1]; // marking 위한 boolean 배열

        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
