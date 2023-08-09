package Baekjoon.정수론.P6064;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1; // 나머지 0 방지
            int y = Integer.parseInt(st.nextToken()) - 1;

            int last = M*N/gcd(M, N);
            boolean isExist = false;
            for (int i = x; i <= last; i += M) {
                if (i % N == y) {
                    isExist = true;
                    sb.append(i+1).append("\n");
                    break;
                }
            }
            if(!isExist) sb.append(-1).append("\n");
        }
        System.out.println(sb);
    }

    public static int gcd(int a, int b) {
        if(b==0) return a;
        else return gcd(b, a % b);
    }
}
