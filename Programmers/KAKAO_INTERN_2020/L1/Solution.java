package Programmers.KAKAO_INTERN_2020.L1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int[][] coordinate=new int[][]{
            {3,1}, //0
            {0,0}, //1
            {0,1}, //2
            {0,2}, //3
            {1,0}, //4
            {1,1}, //5
            {1,2}, //6
            {2,0}, //7
            {2,1}, //8
            {2,2}, //9
            {3,0}, //10
            {3,2}  //11
    };
    static int[] handPosition=new int[2]; // 왼손과 오른속의 현재 위치
    static char mostUsedHand;
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        mostUsedHand = hand.equals("left") ? 'L' : 'R';
        handPosition[0]=10;
        handPosition[1]=11;

        for (int num : numbers) {
            if (determineFinger(num)=='L') {
                sb.append('L');
                handPosition[0]=num;
            }
            else if (determineFinger(num)=='R') {
                sb.append('R');
                handPosition[1]=num;
            }
        }
        return sb.toString();
    }

    public static char determineFinger(int num) {
        if(num==1 || num==4 || num==7) return 'L';
        else if(num==3 || num==6 || num==9) return 'R';
        else {
            if(getDistance(handPosition[0], num) < getDistance(handPosition[1], num)) return 'L';
            if(getDistance(handPosition[0], num) > getDistance(handPosition[1], num)) return 'R';
            return mostUsedHand;
        }
    }

    public static int getDistance(int curPos, int tarPos) {
        return Math.abs(coordinate[curPos][0]-coordinate[tarPos][0]) + Math.abs(coordinate[curPos][1]-coordinate[tarPos][1]);
    }
}
