package Baekjoon.백트래킹.P2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백트래킹 기본 문제
// 1. 전체 순열에서 조건 확인
// 2. 순열 만드는 과정에서 조건 확인
public class Main {
    static int k;
    static String[] inequality_sign;

    static boolean[] isVisited;
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        inequality_sign = new String[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            inequality_sign[i] = st.nextToken();
        }

        isVisited = new boolean[10];
        getPermutation(0, "");
        list.sort(Comparator.comparingLong(Long::parseLong));
        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));
    }

    public static void getPermutation(int depth, String stringPermutation) {
        if (depth == k + 1) {
            if (conditionMeet(stringPermutation)) {
                list.add(stringPermutation);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                getPermutation(depth + 1, stringPermutation + i);
                isVisited[i] = false;
            }
        }
    }

    public static boolean conditionMeet(String stringPermutation) {
        for (int i = 0; i < stringPermutation.length()-1; i++) {
            if (inequality_sign[i].equals("<")) {
                if(stringPermutation.charAt(i)-'0' >= stringPermutation.charAt(i+1)-'0') return false;
            } else if (inequality_sign[i].equals(">")) {
                if(stringPermutation.charAt(i)-'0' <= stringPermutation.charAt(i+1)-'0') return false;
            }
        }
        return true;
    }
}
