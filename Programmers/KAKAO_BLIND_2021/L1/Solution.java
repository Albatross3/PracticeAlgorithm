package Programmers.KAKAO_BLIND_2021.L1;


// 5:15~
// 문자 추가 -> +, StringBuilder 의 append
// 문자 치환 -> replace, replaceAll
// 특정 문자 제거 -> replaceAll or subString
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(	"...!@BaT#*..y.abcdefghijklm"));
    }
    public String solution(String new_id) {
        // 1단계
        new_id = new_id.toLowerCase();
        // 2단계
        String s;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (isLowerCase(c) || isNumber(c) || c == '-' || c == '_' || c == '.') sb.append(c);
        }
        s = sb.toString();
        // 3단계
        s= s.replaceAll("[.]{2,}", ".");
        // 4단계
        if(s.charAt(0)=='.' && s.charAt(s.length()-1)=='.') {
            if(s.length()==1) s = "";
            else s = s.substring(1, s.length() - 1);
        }
        else if(s.charAt(0)=='.') s = s.substring(1, s.length());
        else if(s.charAt(s.length()-1)=='.') s = s.substring(0, s.length() - 1);
        // 5단계
        if(s.length()==0) s += "a";
        // 6단계
        if(s.length() >=16) s = s.substring(0,15);
        if(s.charAt(s.length()-1)=='.') s = s.substring(0, s.length() - 1);
        // 7단계
        char lastChar = s.charAt(s.length() - 1);
        if(s.length()<=2){
            while(s.length()<3)
                s += lastChar;
        }
        return s;
    }
    public static boolean isLowerCase(char c) {
        if(c>=97 && c<=122) return true;
        else return false;
    }

    public static boolean isNumber(char c) {
        if(c>=48 && c<=57) return true;
        else return false;
    }

}
