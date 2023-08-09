package Baekjoon.자료구조.스택.P9935;
// 문자열 길이가 최대 100만이라 replace 함수를 사용하면 메모리 초과 발생
// 스택을 활용해서 특정 문자열 만날 때 pop 나머지 push
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static String S;
    static String bombS;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        S = br.readLine();
        bombS = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            stack.add(S.charAt(i));
            // stack 크기가 폭탄 문자열 길이 이상일때
            if (stack.size() >= bombS.length()) {
                boolean isSame=true;
                for (int j = 0; j < bombS.length(); j++) {
                    if (stack.get(stack.size() - bombS.length() + j) != bombS.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }
                // 일치한다면 모두 pop
                if (isSame) {
                    for (int l = 0; l < bombS.length(); l++) {
                        stack.pop();
                    }
                }
            }
        }
        for (Character c : stack) {
            sb.append(c);
        }
        String answer = sb.toString();
        if(answer.isEmpty()) System.out.println("FRULA");
        else System.out.println(answer);
    }
}
