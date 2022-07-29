package Programmers.CodingTestKit.탐욕법.L1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5, new int[]{2,4}, new int[]{3,1}));
    }
    public int solution(int n, int[] lost, int[] reserve) {
        int countSaved=0;
        int reservedStolen=0;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int number : reserve) {
            linkedList.add(number);
        }
        LinkedList<Integer> linkedList2 = new LinkedList<>();
        for (int number : lost) {
            linkedList2.add(number);
        }
        // 여벌 체육복이 있는데 도난당한 경우
        for (int i = 0; i < lost.length; i++) {
            if(linkedList.remove((Integer)lost[i])) {
                reservedStolen++;
                linkedList2.remove((Integer)lost[i]);
            }
        }
        for(int number:linkedList2){
            if(number==1) {
                if(linkedList.remove((Integer)2)) countSaved++;
            } else if (number== n) {
                if(linkedList.remove((Integer)(n-1))) countSaved++;
            } else {
                if(linkedList.remove((Integer)(number-1))){
                    countSaved++;
                    continue;
                }
                if(linkedList.remove((Integer)(number+1))){
                    countSaved++;
                    continue;
                }
            }
        }

        return n-lost.length+reservedStolen+countSaved;
    }
}
