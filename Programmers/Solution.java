package Programmers;

import java.util.PriorityQueue;
import java.util.TreeMap;

class Solution {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);
        pq.add(1);
        pq.add(2);
        pq.remove(2);
        System.out.println(pq);

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    }
}

