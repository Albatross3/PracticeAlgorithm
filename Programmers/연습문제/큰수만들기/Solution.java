package Programmers.연습문제.큰수만들기;

public class Solution {
    public String solution(String number, int k) {
        int length= number.length() - k; // 뽑아야 하는 개수
        String temp = number.substring(0, k + 1);
        for (int i = 0; i < length; i++) {

            // 주어진 string 에서 최대 정수 찾기
            int max = -1;
            int index = -1;
            for (int j = 0; j < temp.length(); j++) {
                if (max < temp.charAt(j) - '0') {
                    max = temp.charAt(j) - '0';
                    index = j;
                }
            }
            // 주어진 string update
            temp = number.substring(index + 1, k + 1 + i + 1);
        }
        return "";
    }
}
