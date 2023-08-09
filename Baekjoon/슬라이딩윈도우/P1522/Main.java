package Baekjoon.슬라이딩윈도우.P1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        // a의 개수세기
        int countA = 0; // 슬라이딩 윈도우 크기
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'a') countA++;
        }

        int minChange = 1000;

        for (int i = 0; i < s.length(); i++) {
            // 슬라이딩 윈도우 안의 b의 개수 세기
            int countB = 0;
            if (i + countA <= s.length()) {
                for (int j = i; j < i + countA; j++) {
                    if (s.charAt(j) == 'b') countB++;
                }

            } else {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == 'b') countB++;
                }
                for (int j = 0; j < countA - s.length() + i; j++) {
                    if (s.charAt(j) == 'b') countB++;
                }
            }
            minChange = Math.min(countB, minChange);
        }

        System.out.println(minChange);


    }
}
