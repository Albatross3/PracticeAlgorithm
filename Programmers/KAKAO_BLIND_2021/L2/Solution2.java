package Programmers.KAKAO_BLIND_2021.L2;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        String[] answer = sol.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        System.out.println(Arrays.toString(answer));
    }
    static Map<String, Integer> map;
    static boolean[] isVisited;
    static char[] combination;
    public String[] solution(String[] orders, int[] course) {
        List<String> arr = new ArrayList<>();
        // orders 의 원소 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] temp = orders[i].toCharArray();
            Arrays.sort(temp);
            String ordered = new String(temp);
            orders[i]=ordered;
        }

        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            // course[i] 개의 조합 찾기
            for (int j = 0; j < orders.length; j++) {
                if(orders[j].length() < course[i]) continue;
                else if(orders[j].length() == course[i]) map.put(orders[j], 0);
                else {
                    isVisited=new boolean[orders[j].length()+1];
                    combination = new char[course[i] + 1];
                    findComb(orders[j],1,course[i]);
                }
            }

            // order 마다 조합이 몇 개 들어있는지 확인하기
            for (String order : orders) {
                if(order.length() < course[i]) continue;
                else if(order.length() == course[i]) map.put(order, map.get(order) + 1);
                else {
                    for (String comb : map.keySet()) {
                        boolean isContain=true;
                        for (int c = 0; c < comb.length(); c++) {
                            if (!order.contains(Character.toString(comb.charAt(c)))) {
                                isContain=false;
                                break;
                            }
                        }
                        if(isContain) map.put(comb, map.get(comb) + 1);
                    }
                }
            }

            // map 에서 최댓값 뽑고 최댓값에 해당하는 key 추가
            int max=0;
            for (String comb : map.keySet()) {
                max = Integer.max(max, map.get(comb));
            }

            if(max >= 2){
                for (String comb : map.keySet()) {
                    if(map.get(comb)==max)
                        arr.add(comb);
                }
            }
        }
        Collections.sort(arr);
        String[] answer = arr.toArray(new String[arr.size()]);
        return answer;
    }

    public static void findComb(String s, int n, int m) {
        if (n == m + 1) {
            StringBuilder sb = new StringBuilder();
            for(int i=1; i< combination.length; i++)
                sb.append(combination[i]);
            map.put(sb.toString(), 0);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!isVisited[i] && combination[n - 1] < s.charAt(i)) {
                combination[n] = s.charAt(i);
                isVisited[i]=true;
                findComb(s,n+1,m);
                isVisited[i]=false;
            }
        }
    }

}
