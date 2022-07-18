package SWEA.P14555;

// 공과 잡초 D3
// 그냥 구현 문제인듯
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int T;
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            s = br.readLine();
            int minSum=0;
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='(' || s.charAt(i)==')'){
                    minSum++;
                    i++;
                }
            }
            sb.append("#").append(t + 1).append(" ").append(minSum).append("\n");
        }
        System.out.println(sb);
    }
}
