package Baekjoon.문자열.P1316;

// 주어진 문자열에서 중복되지 않는 알파벳 개수 구하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String word;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int result=0;
        for(int i=0; i<N; i++){
            word=br.readLine();
            result+=isGroupWord(word);
        }
        System.out.println(result);
    }
    static int isGroupWord(String s){
        int countAlphabet=0;
        int countDiff=0;
        char[] alphabet=new char[26];
        for(int i=0; i<s.length(); i++){
            int index=s.charAt(i)-'a';
            if(alphabet[index]==0){
                alphabet[index]=1;
            }
        }
        for(int i=0; i<alphabet.length; i++){
            countAlphabet+=alphabet[i];
        }

        // 하나씩 가면서 글자 달라질 때마다 +1
        for(int i=0; i<s.length()-1; i++){
            if (s.charAt(i)!=s.charAt(i+1)) countDiff++;
        }

        // 서로 다른 알파벳 개수 - 1 = 달라지는 글자
        if(countDiff==countAlphabet-1) return 1;
        else return 0;

    }
}
