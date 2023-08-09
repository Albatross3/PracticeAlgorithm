package Baekjoon.자료구조.집합;

// String 배열에서 String 이 들어있는지 확인하는 문제
// 1. 완전탐색
// 2. 이진탐색
// 3. Hashset 에서 contains() 함수 사용 -> 평균적으로 O(1)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static String[] set;
    static int count=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            if(set.contains(br.readLine())) count++;
        }
        System.out.println(count);

    }

}
