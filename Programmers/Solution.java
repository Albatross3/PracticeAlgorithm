package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) {

        // Set<int[]> vs Set<ArrayList<Integer>>
        // 후자는 특정 요소가 들어있는지 확인 가능
        Set<int[]> set = new HashSet<>();
        int[] a1 = {1, 2};
        int[] a2 = {3, 4};

        set.add(a1);
        set.add(a2);

        System.out.println(set);

        int[] b1 = {1, 2};
//        System.out.println(set.contains(b1));

        Set<ArrayList<Integer>> set2 = new HashSet<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        set2.add(list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);

        System.out.println(set2.contains(list2));
    }
}

