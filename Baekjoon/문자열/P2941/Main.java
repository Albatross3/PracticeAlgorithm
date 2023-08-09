package Baekjoon.문자열.P2941;

// 문자열 - replace 함수 사용 문제
// replace() - 특정 문자열을 특정 문자열로 대체함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static String word;
    static String[] alphabet={"c=","c-","dz=","d-","lj","nj","s=","z="};
    static int result=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        word=br.readLine();

        for(int i=0; i<alphabet.length; i++){
            word=word.replace(alphabet[i],"a" );
        }
        System.out.println(word.length());


    }
}
