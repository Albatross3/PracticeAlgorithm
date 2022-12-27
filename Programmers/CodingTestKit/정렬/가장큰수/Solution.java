package Programmers.CodingTestKit.정렬.가장큰수;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public String solution(int[] numbers) {
        List<String> arr = Arrays.stream(numbers).mapToObj(Integer::toString).collect(Collectors.toList());
        Collections.sort(arr, (o1, o2) -> Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2));
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        String answer = sb.toString();
        if (answer.charAt(0) == '0') {
            return "0";
        } else {
            return answer;
        }
    }
}
