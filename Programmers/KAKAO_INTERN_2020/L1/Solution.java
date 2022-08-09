package Programmers.KAKAO_INTERN_2020.L1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println((char)1);
    }
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int[] handPosition=new int[2];
        Map<Integer, int[]> m = new HashMap<>();
        m.put(1,new int[]{1,2,3,4}); m.put(4,new int[]{2,1,2,3}); m.put(7,new int[]{3,2,1,2}); m.put(10,new int[]{4,3,2,1});
        m.put(3,new int[]{1,2,3,4}); m.put(6,new int[]{2,1,2,3}); m.put(9,new int[]{3,2,1,2}); m.put(12,new int[]{4,3,2,1});
        m.put(2,new int[]{0,1,2,3}); m.put(5,new int[]{1,0,1,2}); m.put(8,new int[]{2,1,0,1}); m.put(0,new int[]{3,2,1,0});
        handPosition[0]=10;
        handPosition[1]=12;

        Map<Integer, Integer> relation = new HashMap<>();
        relation.put(2,0); relation.put(5,1); relation.put(8,2); relation.put(0,3);
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i]==1||numbers[i]==4 ||numbers[i]==7) {
                handPosition[0]=numbers[i];
                sb.append('L');
            }
            else if(numbers[i]==3||numbers[i]==6||numbers[i]==9){
                handPosition[1]=numbers[i];
                sb.append('R');
            }else{
                int number=numbers[i];
                int index = relation.get(number);
                int leftDistance=m.get(handPosition[0])[index];
                int rightDistance=m.get(handPosition[1])[index];

                if(leftDistance==rightDistance) {
                    if(hand.equals("left")) {
                        sb.append('L');
                        handPosition[0]=number;
                    }
                    else if (hand.equals("right")) {
                        sb.append('R');
                        handPosition[1]=number;
                    }
                }
                else if(leftDistance<rightDistance) {
                    sb.append('L');
                    handPosition[0]=number;
                }else{
                    sb.append('R');
                    handPosition[1]=number;
                }
            }
        }
        return sb.toString();
    }
}
