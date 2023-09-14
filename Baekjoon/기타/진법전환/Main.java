package Baekjoon.기타.진법전환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/*
  진법 전환
  1. N 진법을 10진법으로 -> Integer.parseInt(String s , int radix) 활용
  2. 10 진법을 N 진법으로 -> 아래 코드와 같음
 */
public class Main {
    static int N;
    static int B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i + 10, (char) ('A' + i));
        }

        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            int q = N % B;
            if(q<10) sb.append(q);
            else sb.append(map.get(q));
            N = N / B;
        }
        System.out.println(sb.reverse());

    }
}
