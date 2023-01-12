package Programmers;

import java.util.*;

public class Solution {


    public int[] solution(String[] info, String[] query) {
        String[] language = {"-", "cpp", "java", "python"};
        String[] position = {"-", "backend", "frontend"};
        String[] career = {"-", "junior", "senior"};
        String[] food = {"-", "chicken", "pizza"};
        // 모든 가능한 조합을 key 로 만들기
        Map<String, ArrayList<Integer>> combination = new LinkedHashMap<>();
        for (int i = 0; i < language.length; i++) {
            for (int j = 0; j < position.length; j++) {
                for (int k = 0; k < career.length; k++) {
                    for (int m = 0; m < food.length; m++) {
                        String temp = language[i] + " " + position[j] + " " + career[k] + " " + food[m];
                        combination.put(temp, new ArrayList<>());
                    }
                }
            }
        }

        // info 정보에 따라 map 채우기
        for (String s : info) {
            String[] tempInfo = s.split(" ");
            String l = tempInfo[0];
            String p = tempInfo[1];
            String c = tempInfo[2];
            String f = tempInfo[3];
            int X = Integer.parseInt(tempInfo[4]);
            String[] tempL = {"-", l};
            String[] tempP = {"-", p};
            String[] tempC = {"-", c};
            String[] tempF = {"-", f};
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        for (int m = 0; m < 2; m++) {
                            String temp = tempL[i] + " " + tempP[j] + " " + tempC[k] + " " + tempF[m];
                            combination.get(temp).add(X);
                        }
                    }
                }
            }
        }

        // map 에 따른 정렬
        for (String key : combination.keySet()) {
            Collections.sort(combination.get(key));
        }



        // query 에 대해서 검색
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] s = query[i].split(" ");
            String l = s[0];
            String p = s[2];
            String c = s[4];
            String f = s[6];
            int X = Integer.parseInt(s[7]);
            String temp = l + " " + p + " " + c + " " + f;
            ArrayList<Integer> arr = combination.get(temp);
            answer[i] = arr.size() - lowerBound(arr, X);
        }

        return answer;
    }

    public static int lowerBound(ArrayList<Integer> arr, int x) {
        int size = arr.size();
        int lo = -1, hi = size;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if(!(arr.get(mid)>=mid)) lo = mid;
            else hi = mid;
        }
        return hi;
    }

}
