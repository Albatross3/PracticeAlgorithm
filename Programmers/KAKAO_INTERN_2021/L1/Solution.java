package Programmers.KAKAO_INTERN_2021.L1;

import java.util.HashMap;
import java.util.Map;

// Stringbuilder 는 String 과 class 자체가 다름
public class Solution {
    public static void main(String[] args) {

        System.out.println(solution2("23four5six7"));
        System.out.println(solution2("2three45sixseven"));
        System.out.println(solution2("123"));
    }

    public static int solution(String s) {
        StringBuilder sb = new StringBuilder();
        Map<String,Integer> m=new HashMap<>();
        m.put("zero",0); m.put("one",1); m.put("two",2); m.put("three",3); m.put("four",4);
        m.put("five",5); m.put("six",6); m.put("seven",7); m.put("eight",8); m.put("nine",9);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 문자인 경우
            if (c >= 97) {
                StringBuilder temp = new StringBuilder();
                while (c >= 97) {
                    temp.append(c);
                    if(m.get(temp.toString())!=null) break;
                    i++;
                    c=s.charAt(i);
                }
                sb.append(m.get(temp.toString()));

            }
            // 숫자인 경우
            else sb.append(c);
        }
        return Integer.parseInt(sb.toString());
    }
    // 문자열 함수를 이용한 더 쉬운 풀이
    public static int solution2(String s){
        String[] digits={"0","1","2","3","4","5","6","7","8","9"};
        String[] words={"zero","one","two","three","four","five","six","seven","eight","nine"};

        for(int i=0; i<10; i++){
            s=s.replace(words[i],digits[i]);
        }
        return Integer.parseInt(s);
    }

}
