package Programmers.KAKAO_BLIND_2020.괄호변환;

import java.util.Stack;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.solution("()))((()"));
    }
    public String solution(String p){
        // 1단계
        if(p.isEmpty()) return "";
        // 2단계
        String u="", v = "";
        int numLeft=0, numRight=0;
        for (int i = 0; i < p.length(); i++) {
            u += p.charAt(i);
            if(p.charAt(i)=='(') numLeft++;
            else if(p.charAt(i)==')') numRight++;

            if(numLeft==numRight) {
                if(u.length() < p.length())
                    v=p.substring(i+1);
                break;
            }

        }
        // 3단계
        if (isRight(u)) {
            return u + solution(v);
        }
        // 4단계
        else {
            String x = "";
            x += '(';
            x += solution(v);
            x += ')';
            u = u.substring(1, u.length() - 1);
            for(int i=0; i<u.length(); i++){
                if(u.charAt(i)=='(') x += ')';
                else if(u.charAt(i)==')') x+='(';
            }
            return x;
        }

    }
    public static boolean isRight(String p) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i)=='(') stack.push('(');
            else if(p.charAt(i)==')') {
                // 개선이 필요한듯
                if(stack.isEmpty()) return false;
                else {
                    if(stack.peek()=='(') stack.pop();
                    else if(stack.peek()==')') stack.push(')');
                }
            }
        }
        return stack.isEmpty();
    }
}
