package Programmers.KAKAO_BLIND_2019.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int column;
    static int[] number;
    static int[] comb;
    static boolean[] isVisited;

    static Set<ArrayList<Integer>> candidateKey = new HashSet<>();
    static ArrayList<ArrayList<Integer>> arrayLists;
    static int answer = 0;
    public int solution(String[][] relation) {
        column = relation[0].length;
        number = new int[column + 1];
        for (int i = 0; i < number.length; i++) {
            number[i] = i;
        }
        for (int i = 1; i <= column; i++) {
            arrayLists = new ArrayList<>();
            comb = new int[i+1];
            isVisited = new boolean[number.length]; // back 돌리기 전 초기화
            back(1,i);
            for (ArrayList<Integer> temp : arrayLists) {
                Set<ArrayList<String>> set = new HashSet<>();
                boolean isUnique=true;
                for (int r = 0; r < relation.length; r++) {
                    ArrayList<String> tuple = new ArrayList<>();
                    for (int index : temp) {
                        tuple.add(relation[r][index-1]);
                    }
                    if(!set.add(tuple)) {
                        isUnique = false;
                        break;
                    }
                }
                if(isUnique) {
                    answer++;
                    candidateKey.add(temp);
                }
            }
        }

        return answer;
    }

    public static void back(int depth,int target) {
        if (depth == target + 1) {
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 1; i < comb.length; i++) {
                arr.add(comb[i]);
            }

            // 최소성 검증
            for (ArrayList<Integer> candidate : candidateKey) {
                boolean isContain=true;
                for (int num : candidate) {
                    if(!arr.contains(num)) {
                        isContain=false;
                    }
                }
                if (isContain) return;
            }
            arrayLists.add(arr);
            return;
        }
        for (int i = 1; i < number.length; i++) {
            if (!isVisited[i] && comb[depth-1] < number[i]) {
                comb[depth]=number[i];
                isVisited[i]=true;
                back(depth+1,target);
                isVisited[i]=false;
            }
        }
    }
}
