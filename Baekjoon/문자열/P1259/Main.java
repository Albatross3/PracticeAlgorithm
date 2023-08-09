package Baekjoon.문자열.P1259;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String numS;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while(true){
            numS=br.readLine();
            if(Integer.parseInt(numS)==0) break;

            if(isPalindrome(numS)){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        }
    }
    static boolean isPalindrome(String s){
        char[] arr=s.toCharArray();
        char[] reversedArr=new char[arr.length];
        for(int i=0; i<arr.length; i++){
            reversedArr[i]=arr[arr.length-1-i];
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=reversedArr[i]){
                return false;
            }
        }
        return true;
    }
}
