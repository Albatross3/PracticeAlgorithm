package Baekjoon.문자열.P1152;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String s;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        s=br.readLine();
        int result=0;
        int length=s.length();
        for(int i=0; i<length; i++){
            if(String.valueOf(s.charAt(i)).equals(" ")){
                result++;
            }
        }
        if(String.valueOf(s.charAt(0)).equals(" ")){
            if(String.valueOf(s.charAt(length-1)).equals(" ")){
                System.out.println(result-1);
            }else{
                System.out.println(result);
            }
        }else{
            if(String.valueOf(s.charAt(length-1)).equals(" ")){
                System.out.println(result);
            }else{
                System.out.println(result+1);
            }
        }



    }

}
