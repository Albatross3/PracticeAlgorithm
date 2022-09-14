package Programmers;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        String s = "  A  Bc  ";
        String[] arr = s.split(" ");
        for (String a : arr) {
            System.out.print("["+a+"]");
        }
        System.out.println(arr.length);
    }
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append(' ');
            } else {
                StringBuilder temp = new StringBuilder();
                while (s.charAt(i) != ' ') {
                    temp.append(s.charAt(i));
                    String sub = changeToJadenCase(temp.toString());
                    sb.append(sub);
                    i++;
                }
                i -= 1;
            }
        }
        return sb.toString();
    }
    public static String changeToJadenCase(String s) {
        StringBuilder part = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

        }
        return part.toString();
    }

}
