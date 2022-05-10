package AlgorithmStudy.문자열.P1541;

// 문자열 - split 함수 사용법
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] minusEx = br.readLine().split("-");
        int result = 0;
        for (int i = 0; i < minusEx.length; i++) {
            int temp = 0;
            // + 는 meta character 이기 때문에 이스케이프 해야한다
            String[] plusEx = minusEx[i].split("\\+");
            for (int j = 0; j < plusEx.length; j++) {
                temp += Integer.parseInt(plusEx[j]);
            }
            if(i==0) result=temp;
            else result-=temp;

        }
        System.out.println(result);
    }
}
