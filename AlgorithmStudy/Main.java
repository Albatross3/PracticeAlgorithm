package AlgorithmStudy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static boolean[] card;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        card=new boolean[20000001];
        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card[Integer.parseInt(st.nextToken())+10000000]=true;
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if(card[Integer.parseInt(st.nextToken())+10000000]) sb.append(1+" ");
            else sb.append(0 + " ");
        }
        System.out.println(sb);
    }
}
