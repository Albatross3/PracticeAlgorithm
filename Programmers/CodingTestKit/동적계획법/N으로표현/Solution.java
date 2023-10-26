package Programmers.CodingTestKit.동적계획법.N으로표현;

import java.util.*;
public class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> setList = new ArrayList<>();
        for(int i=0; i<=8; i++) {
            setList.add(new HashSet<>());
        }

        for(int i=1; i<=8; i++) {
            String cont = String.valueOf(N).repeat(i);
            setList.get(i).add(Integer.parseInt(cont));
        }

        for(int i=1; i<=8; i++) {
            Set<Integer> curSet = setList.get(i);
            for(int j=1; j<i; j++) {
                Set<Integer> set1 = setList.get(j);
                Set<Integer> set2 = setList.get(i-j);
                for(int x1 : set1) {
                    for(int x2 : set2) {
                        curSet.add(x1+x2);
                        curSet.add(x1-x2);
                        curSet.add(x1*x2);
                        if(x2!=0) curSet.add(x1/x2);
                    }
                }
            }
        }

        // number 찾기
        for(int i=1; i<9; i++) {
            if(setList.get(i).contains(number)) return i;
        }
        return -1;
    }
}
