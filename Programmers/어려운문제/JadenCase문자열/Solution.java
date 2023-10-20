package Programmers.어려운문제.JadenCase문자열;
// 공백까지 다 구분해서 나오게 하는 방법 -> s.split(""); <trailing 도 가능>
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("3people unFollowed me"));
    }
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] arr = s.toLowerCase().split("");
        boolean flag = true;
        for (String temp : arr) {
            if (temp.equals(" ")) {
                sb.append(" ");
                flag = true;
            } else {
                if (flag) {
                    sb.append(temp.toUpperCase());
                } else {
                    sb.append(temp);
                }
                flag = false;
            }
        }
        return sb.toString();
    }
}

