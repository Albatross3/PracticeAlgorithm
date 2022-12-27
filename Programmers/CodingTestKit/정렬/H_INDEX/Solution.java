package Programmers.CodingTestKit.정렬.H_INDEX;

import java.util.Arrays;

public class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int citedPaper = citations.length - i;
            if (citations[i] > citedPaper) {
                answer = citedPaper;
                break;
            }
        }
        return answer;
    }
}
