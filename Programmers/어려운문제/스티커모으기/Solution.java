package Programmers.어려운문제.스티커모으기;

public class Solution {
    public int solution(int sticker[]) {
        int length = sticker.length;
        if(length==1) return sticker[0];
        int[] firstSelectDP = new int[length];
        int[] firstNotSelectDP = new int[length];

        // 첫 번째를 뽑는 경우
        firstSelectDP[0] = sticker[0];
        firstSelectDP[1] = firstSelectDP[0];
        for(int i=2; i<length-1; i++) {
            firstSelectDP[i] = Math.max(firstSelectDP[i-2] + sticker[i], firstSelectDP[i-1]);
        }

        // 첫 번째를 뽑지 않는 경우
        firstNotSelectDP[0] = 0;
        firstNotSelectDP[1] = sticker[1]; // 최대이므로
        for(int i=2; i<length; i++) {
            firstNotSelectDP[i] = Math.max(firstNotSelectDP[i-2] + sticker[i], firstNotSelectDP[i-1]);
        }

        int answer = Math.max(firstSelectDP[length-2], firstNotSelectDP[length-1]);
        return answer;
    }
}
