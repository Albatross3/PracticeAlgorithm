package Programmers.CodingTestKit.정렬.K번째수;

// int 배열로 반환하는 경우
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] array={1,5,2,6,3,7,4};
        int[][] commands={{2,5,3},{4,4,1},{1,7,3}};
        int[] answer=solution(array,commands);
    }
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer=new int[commands.length];
        for(int c=0; c< commands.length; c++){
            int i=commands[c][0];
            int j=commands[c][1];
            int k=commands[c][2];
            int[] subArray=new int[j-i+1];
            for(int s=0; s<=j-i; s++){
                subArray[s]=array[s+i-1];
            }
            Arrays.sort(subArray);
            answer[c]= subArray[k-1];
        }
        return answer;
    }
}
