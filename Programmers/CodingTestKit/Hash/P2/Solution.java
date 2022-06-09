package Programmers.CodingTestKit.Hash.P2;

// 1. 2중 for문
// 2. 해시 방식
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {

    }
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        loop:
        for(int i=0; i<phone_book.length-1; i++){
            for(int j=i; j<=phone_book.length-1; j++){
                if(phone_book[j+1].startsWith(phone_book[i])){
                    answer=false;
                    break loop;
                }
            }
        }

        return answer;
    }
    public static boolean isPrefix(String target,String standard){
        boolean answer=true;
        for(int i=0; i<target.length(); i++){
            if(target.charAt(i)!=standard.charAt(i)){
                answer=false;
                break;
            }
        }
        return answer;
    }
}
