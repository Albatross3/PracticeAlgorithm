package Programmers.KAKAO_BLIND_2021.신규아이디추천;

public class Main {
    public static void main(String[] args) {
        String s="...!@BaT#*..y.abcdefghijklm";
        s = s.replaceAll("[.]{2,}", ".");
        System.out.println(s);
    }
    public String solution(String new_id) {
        // 1단계
        String s= new_id.toLowerCase();
        // 2단계
        s = s.replaceAll("[^-_.a-z0-9]", "");
        // 3단계
        s = s.replaceAll("[.]{2,}", ".");
        // 4단계
        s = s.replaceAll("^[.]|[.]$", "");
        // 5단계
        if(s.isEmpty()) s += "a";
        // 6단계
        if(s.length() >= 16) s = s.substring(0, 15);
        s = s.replaceAll("[.]$", "");
        // 7단계
        if (s.length() <= 2) {
            while (s.length() < 3)
                s += s.charAt(s.length() - 1);
        }
        return s;
    }
}
