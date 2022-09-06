package Programmers.CodingTestKit.탐욕법.조이스틱;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("JAN")); // 갔다가 돌아오는 경우도 있음
    }

    public int solution(String name) {
        // 좌우 움직이는 시간 최소 구하기

        // A 가 아닐 때의 위치값들을 담기
        for (int i = 1; i < name.length(); i++) {
            if(name.charAt(i)!='A')
                arr.add(i);
        }
        // 오른쪽으로 갈 때 최솟값
        int rightMin, leftMin;
        if(arr.size()==0) rightMin=0;
        else rightMin = arr.get(arr.size() - 1);
        // 왼쪽으로 갈 때 최솟값
        if(arr.size()==0) leftMin=0;
        else leftMin = name.length() - arr.get(0);

        int min = Math.min(rightMin, leftMin);
        // 오른쪽으로 갔다가 왼쪽으로 돌아오는 값들
        ArrayList<Integer> rightToLeft = new ArrayList<>();
        for (int i = 0; i < arr.size()-1; i++) {
            rightToLeft.add(arr.get(i) * 2 + name.length() - arr.get(i + 1));
        }
        Collections.sort(rightToLeft);
        if(rightToLeft.size()!=0) min = Math.min(min, rightToLeft.get(0));
        // 왼쪽으로 갔다가 오른쪽으로 돌아오는 값들
        ArrayList<Integer> leftToRight = new ArrayList<>();
        for (int i = arr.size()-1; i >= 1; i--) {
            leftToRight.add((name.length() - arr.get(i)) * 2 + arr.get(i - 1));
        }
        Collections.sort(leftToRight);
        if(leftToRight.size()!=0) min = Math.min(min, leftToRight.get(0));
        // 위 아래 얼마나 움직이는 시간
        int upDownCount = 0;
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i) < 'N') upDownCount += name.charAt(i) - 'A';
            else if(name.charAt(i) > 'N') upDownCount += 91 - name.charAt(i);
            else upDownCount += 13;
        }
        return min + upDownCount;
    }
}
