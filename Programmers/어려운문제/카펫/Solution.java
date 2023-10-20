package Programmers.어려운문제.카펫;

import java.util.ArrayList;

public class Solution {
    public int[] solution(int brown, int yellow) {
        ArrayList<int[]> comb1 = new ArrayList<>();
        ArrayList<int[]> comb2 = new ArrayList<>();
        int total = brown + yellow;
        for (int i = 3; i <= (int) Math.sqrt(total); i++) {
            if (total % i == 0) {
                int[] temp = new int[2];
                temp[0] = i;
                temp[1] = total / i;
                comb1.add(temp);
            }
        }
        for (int i = 1; i <= (int) Math.sqrt(yellow); i++) {
            if (yellow % i == 0) {
                int[] temp = new int[2];
                temp[0] = i;
                temp[1] = yellow / i;
                comb2.add(temp);
            }
        }

        int[] answer = new int[2];
        for (int i = 0; i < comb1.size(); i++) {
            int[] temp = comb1.get(i);
            int h = temp[0];
            int w = temp[1];
            boolean isFind=false;
            for (int j = 0; j < comb2.size(); j++) {
                if (comb2.get(j)[0] == h - 2 && comb2.get(j)[1] == w - 2) {
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }
        return answer;
    }
}
