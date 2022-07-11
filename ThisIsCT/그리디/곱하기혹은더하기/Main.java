package ThisIsCT.그리디.곱하기혹은더하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        int answer=0;
        for (int i = 0; i < s.length(); i++) {
            int temp = s.charAt(i) - '0';
            if (answer <= 1 || temp <= 1) {
                answer += temp;
            } else {
                answer*=temp;
            }
        }
        System.out.println(answer);
    }
}
