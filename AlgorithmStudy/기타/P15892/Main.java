package AlgorithmStudy.기타.P15892;

// 모듈러 연산 - 자바 정수의 범위 고려해야함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int L;
    static char[] string;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        L=Integer.parseInt(br.readLine());
        string=new char[L];
        // br.read() -> 1글자씩 int로 읽어들인다
        for(int i=0; i<L; i++){
            string[i]=(char) br.read();
        }
        sb.append(hash(string));
        System.out.println(sb);
    }
    static int hash(char[] arr){
        double result=0;
        double r=1;
        int mod=1234567891;
        for(int i=0; i<arr.length; i++){
            result+=(arr[i]-'a'+1)*r%mod;
            r=r*31%mod;
        }
        result=result%mod;
        return (int)result;
    }
}
