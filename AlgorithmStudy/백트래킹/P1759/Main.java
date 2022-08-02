package AlgorithmStudy.백트래킹.P1759;


// 삼성 SDS 알고리즘 특강 문제
// 암호 만들기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L,C;
    static char[] sequence;

    static boolean[] isUsed;
    static char[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sequence = new char[C];
        for (int i = 0; i < C; i++) {
            sequence[i] = st.nextToken().charAt(0);
        }

        //
        Arrays.sort(sequence); // a c i s t w
        isUsed = new boolean[C]; // a c i s t w
        result = new char[L+1]; // char 초기화한 값은 NUL 로서 숫자로는 0

        combination(1); // NUL a c i s
        System.out.println(sb);
    }

    public static void combination(int n) {
        if(n==L+1){
            String result2 = new String(result, 1, L );
            if(isSatisfied(result2)) sb.append(result2).append("\n");
            return;
        }
        for (int i = 0; i < C; i++) {
            if (!isUsed[i] && result[n-1] < sequence[i] ) {
                result[n]=sequence[i];
                isUsed[i]=true;
                combination(n+1);
                isUsed[i]=false;
            }
        }
    }

    public static boolean isSatisfied(String result) {
        int countConsonants=0;
        int countVowels=0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'a' || result.charAt(i) == 'e' || result.charAt(i) == 'i' || result.charAt(i) == 'o' || result.charAt(i) == 'u') {
                countVowels++;
            }
            else countConsonants++;
        }
        if(countConsonants >=2 && countVowels >=1) return true;
        else return false;
    }
}
