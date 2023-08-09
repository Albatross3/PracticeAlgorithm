package Baekjoon.정규표현식;

// 사탕 종류 -> C, P, Z, Y
// 정규표현식 문제는 아니지만
// for 문 안에 정규표현식을 넣어서 구성하게 되면 메모리 초과가 발생함
// 정규표현식에 사용되는 Matcher 가 메모리를 많이 차지하는 듯
// Garbage Collector 가 실행되기도 전에 메모리가 다 차는 듯

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int N;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    static Pattern pattern = Pattern.compile("([C]+)|([P]+)|([Z]+)|([Y]+)");
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int MAX = 0;
        // 행이랑 바꾸기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                // 인접한 사탕끼리 색 다른 경우
                if (map[i][j] != map[i][j + 1]) {
                    char temp = map[i][j];
                    map[i][j] = map[i][j + 1];
                    map[i][j + 1] = temp;
                    // 인접한 것끼리 바꾸고 지울 수 있는 최대 길이 찾기
                    MAX = Math.max(MAX, maxCandy());
                    // 원래대로 돌리기
                    map[i][j + 1] = map[i][j];
                    map[i][j] = temp;
                }
            }
        }
        // 열이랑 바꾸기
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N-1; i++) {
                // 인접한 사탕끼리 색 다른 경우
                if (map[i][j] != map[i + 1][j]) {
                    char temp = map[i][j];
                    map[i][j] = map[i + 1][j];
                    map[i + 1][j] = temp;
                    // 인접한 것끼리 바꾸고 지울 수 있는 최대 길이 찾기
                    MAX = Math.max(MAX, maxCandy());
                    // 원래대로 돌리기
                    map[i + 1][j] = map[i][j];
                    map[i][j] = temp;
                }
            }
        }
        System.out.println(MAX);
    }

    public static int maxCandy() {
        int max = 0;
        // 행에서 최대 구하기
        for (int i = 0; i < N; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            max = Math.max(max, getMax(sb.toString()));
        }

        // 열에서 최대 구하기
        for (int j = 0; j < N; j++) {
            sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(map[i][j]);
            }
            max = Math.max(max, getMax(sb.toString()));
        }
        return max;
    }

    public static int getMax(String s) {
        int temp = 0;
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            temp = Math.max(temp, matcher.group().length());
        }
        matcher = null;
        return temp;
    }
}
