package Baekjoon.문자열.P1157;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] num;
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        s=br.readLine();
        int count=0;
        int lastIndex=0;
        num=new int[26];

        // 단어내 알파벳 개수 뽑아내기
        for(int i=0; i<s.length(); i++){
            int index=findIndex(s.charAt(i));
            num[index]++;
        }

        // 최댓값과 최댓값에 해당하는 index 찾기
        int max=num[0];
        for(int i=0; i<26; i++){
            if(num[i]>max){
                max=num[i];
                lastIndex=i;
            }
        }

        // 최댓값이 2개 이상인지 확인하기 위함
        for(int i=0; i<26; i++){
            if(num[i]==max){
                count++;
            }

        }

        //
        if(count!=1){
            System.out.println("?");
        }else {
            System.out.println((char)(lastIndex+65));
        }
//        System.out.println('a'-'0');
//        System.out.println('A'-'0'); // '0':48  'A':65 'a':97
//        System.out.println(findIndex('B'));
    }
    static int findIndex(char c){
        int x=c-'0';
        if(x>=49){
            return x-49;
        }else{
            return x-17;
        }
    }
}
