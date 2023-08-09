package Baekjoon.백트래킹.P1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int L,C;
    static char[] data;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        data = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            data[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(data);
        dfs(0,0,0,-1,"");
        System.out.println(sb);
    }

    public static void dfs(int length, int ja, int mo, int current, String pwd) {
        // 1. 체크인
        // 2. 목적지인가? -> 길이 + 자음,모음 개수
        if(length==L) {
            if(ja>=2 && mo>=1){
                sb.append(pwd).append("\n");
            }
        }
        // 3. 연결된 곳 순회 -> 높은 알파벳 부터
        for (int nextIndex = current + 1; nextIndex < data.length; nextIndex++) {
            char nextData = data[nextIndex];
            // 4. 갈 수 있는가?
            // 5. 간다
            if (nextData == 'a' || nextData == 'e' || nextData == 'i' || nextData == 'o' || nextData == 'u') {
                dfs(length + 1, ja, mo + 1, nextIndex, pwd + nextData);
            } else {
                dfs(length+1,ja+1,mo,nextIndex,pwd+nextData);
            }
        }

        // 6. 체크 아웃
    }
}
