package Programmers.어려운문제.요격시스템;

import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[0]==o2[0]) {
                return o1[1] - o2[1];
            } else return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int[] target : targets) {
            if(pq.isEmpty()) {
                pq.add(target[1]);
                continue;
            }
            if(target[0] < pq.peek()) {
                int end = pq.poll();
                pq.add(Math.min(target[1], end));
            } else {
                pq.add(target[1]);
            }
        }
        return pq.size();
    }
}
