package Baekjoon.문자열.P2675;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int R;
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
//        StringBuilder sb=new StringBuilder();
        T=Integer.parseInt(br.readLine());
        for(int n=0; n<T; n++) {
            st=new StringTokenizer(br.readLine());
            R=Integer.parseInt(st.nextToken());
            S= st.nextToken();

            // 문자열 1개에 대하여 반복
            for(int i=0; i<S.length(); i++){
                for(int j=0; j<R; j++){
                    bw.write(S.charAt(i));
                }
            }
            bw.write('\n');
        }
        bw.flush();
        bw.close();
    }
}
