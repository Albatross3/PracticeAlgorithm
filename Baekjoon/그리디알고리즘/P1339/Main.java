package Baekjoon.그리디알고리즘.P1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 그리디 알고리즘 기본 문제
public class Main {
    static int N;
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                map.put(c, map.getOrDefault(c, 0) + (int) Math.pow(10, words[i].length() - j - 1));
            }
        }
        List<WordNumber> list = new ArrayList<>();
        for (char c : map.keySet()) {
            list.add(new WordNumber(c, map.get(c)));
        }
        list.sort((o1, o2) -> o2.number - o1.number);
        int max = 0;
        int number = 9;
        for (WordNumber wordNumber : list) {
            max += wordNumber.number * number--;
        }
        System.out.println(max);
    }
    static class WordNumber {
        char alphabet;
        int number;

        public WordNumber(char alphabet, int number) {
            this.alphabet = alphabet;
            this.number = number;
        }
    }

}
