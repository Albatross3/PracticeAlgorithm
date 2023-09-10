package Baekjoon.자료구조.맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// TreeMap -> 정렬된 map 이라고 생각 (오름차순)
public class Main {
    static int T;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // 테스트 케이스 1개에 대하여
            k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < k; i++) {
                String[] split = br.readLine().split(" ");
                String operation = split[0];
                int number = Integer.parseInt(split[1]);

                if (operation.equals("I")) {
                    treeMap.put(number, treeMap.getOrDefault(number, 0) + 1);
                } else if (operation.equals("D")) {
                    if(treeMap.isEmpty()) continue;

                    int removedNumber = number == 1 ? treeMap.lastKey() : treeMap.firstKey();
                    if (treeMap.get(removedNumber) == 1) {
                        treeMap.remove(removedNumber);
                    } else {
                        treeMap.put(removedNumber, treeMap.get(removedNumber) - 1);
                    }
                }
            }
            if(treeMap.isEmpty()) sb.append("EMPTY").append("\n");
            else sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
        }
        System.out.println(sb);
    }
}
