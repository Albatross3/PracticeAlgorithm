package Programmers.KAKAO_BLIND_2018.다트게임;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("1S*2T*3S"));
    }
    public int solution(String dartResult) {
        int[] answer = new int[3];
        Pattern pattern = Pattern.compile("([0-9]{1,2})([SDT])([*#]?)");
        Matcher matcher = pattern.matcher(dartResult);
        int index = 0;
        while (matcher.find()) {
            answer[index] = Integer.parseInt(matcher.group(1));
            switch (matcher.group(2)) {
                case "S":
                    answer[index] = (int) Math.pow(answer[index], 1);
                    break;
                case "D":
                    answer[index] = (int) Math.pow(answer[index], 2);
                    break;
                case "T":
                    answer[index] = (int) Math.pow(answer[index], 3);
                    break;
            }
            switch (matcher.group(3)) {
                case "*":
                    if (index != 0) {
                        answer[index - 1] *= 2;
                    }
                    answer[index] *= 2;
                    break;
                case "#":
                    answer[index] *= -1;
                    break;
            }
            index++;
        }
        return answer[0] + answer[1] + answer[2];
    }

}
