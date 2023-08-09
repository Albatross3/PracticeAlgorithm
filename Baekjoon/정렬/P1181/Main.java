package Baekjoon.정렬.P1181;

// 백준 1181번 단어 정렬
// 1. Comparator 인터페이스의 구현 -> compare 함수 구현
// 2. String class 에서 compareTo 함수의 의미 ( 사전 순 정렬 가능 )
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        N=Integer.parseInt(br.readLine());
        arr=new String[N];
        for(int i=0; i<N; i++){
            arr[i]=br.readLine();
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()==o2.length()) return o1.compareTo(o2);
                else return o1.length()-o2.length();
            }
        });

        sb.append(arr[0]).append("\n");
        for(int i=1; i<N; i++){
            if(!arr[i].equals(arr[i-1]))
                sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }
}

