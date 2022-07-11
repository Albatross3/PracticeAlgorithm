package Programmers.KAKAO_BLIND_2020.L2;

import java.util.Stack;
// refactoring -> isSplit() 함수 필요없음 <문제 잘못 이해>
// static 으로 정의된 variable 은 길게 쓰는 것이 좋다
// 괄호 변환
public class Solution {
    public static void main(String[] args) {
//        System.out.println(isRight("()((()))"));
        Solution s = new Solution();
        System.out.println(s.solution("()))((()"));
    }
    public String solution(String p) {
        StringBuilder sb = new StringBuilder();
        if(p.isEmpty()) return ""; // 빈 문자열
        else if(isRight(p)) return p; // 올바른 문자열
        // 균형잡힌 문자열
        else return change(p);

    }
    public static String change(String s) {
        String u="",v="";
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                if(s.isEmpty()) return "";
                int countLeft=0,countRight=0;
                for (int i = 0; i < s.length(); i++) {
                    sb.append(s.charAt(i));
                    if(s.charAt(i)=='(') countLeft++;
                    else countRight++;

                    if(countLeft==countRight){
                        // 분리할 수 없다면
                        if(!isSplit(sb.toString())) {
                            u=s.substring(0,i+1);
                            v = s.substring(i + 1);
                            break;
                        }
                        else continue;
                    }
                }
                if(isRight(u)) return sb.append(change(v)).toString();
                else{
                    sb2.append('(');
                    sb2.append(change(v));
                    sb2.append(')');
                    String u2 = u.substring(1, u.length() - 1);
                    for (int i = 0; i < u2.length(); i++) {
                        if(u2.charAt(i)=='(') sb2.append(')');
                else sb2.append('(');
            }
            return sb2.toString();
        }
    }
    public static boolean isSplit(String s) {
        boolean isSplit=false;
        int countLeft=0,countRight=0;
        for (int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i)=='(') countLeft++;
            else countRight++;

            if(countLeft==countRight) {
                isSplit=true;
            }
        }
        return isSplit;
    }
    public static boolean isRight(String s) {
        boolean isRight=true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='(') stack.push('(');
            else {
                if(stack.isEmpty()) {
                    stack.push(')');
                    continue;
                }
                if(stack.peek()=='(') stack.pop();
                else stack.push(')');
            }
        }
        return stack.isEmpty();
    }
}
