package Programmers.KAKAO_BLIND_2021.메뉴리뉴얼;

// 풀이 설계
// course 크기만큼 반복
// 1. orders 로 course[i] 만큼의 길이를 갖는 조합을 생성
// 2. 해당 조합의 모든 원소를 메뉴가 몇개 가지고 있는지 map 에 생성
// 3. 최댓값에 해당하는 조합만을 최종 답에 추가
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] a=solution.solution(new String[]{"XYZ", "XWY", "WXA"},
                new int[]{2,3,4});
        System.out.println(Arrays.toString(a));



    }

    static Map<String, Integer> map = new HashMap<>();
    static String s = "";
    static char[] comb;
    static boolean[] isVisited;
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> arr = new ArrayList<>();
        boolean[] alphabet = new boolean[26]; // 0-A , 25-Z 에 각각 대응
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            for (int j = 0; j < order.length(); j++) {
                int index = order.charAt(j) - 'A';
                if(!alphabet[index]) alphabet[index]=true;
            }
        }
        for (int i = 0; i < 26; i++) {
            if(alphabet[i]) s += (char)('A'+i);
        }
        // orders 정렬
        for (int i = 0; i < orders.length; i++) {
            String origin = orders[i];
            char[] ordered = origin.toCharArray();
            Arrays.sort(ordered);
            String sortedString = new String(ordered);
            orders[i] = sortedString;
        }

        for (int i = 0; i < course.length; i++) {
            comb = new char[course[i]+1];
            isVisited = new boolean[s.length()];

            // 조합 뽑기
            combination(1,course[i]);

            // 각 조합을 얼마나 가지고 있는지 확인하기
            for (int c = 0; c < orders.length; c++) {
                // 조합 길이 보다 작은 경우 -> pass
                if(orders[c].length() < course[i]) continue;
                // 조합 길이와 같은 경우
                else if(orders[c].length() == course[i]) {
                    map.put(orders[c], map.get(orders[c]) + 1);
                }
                // 조합 길이보다 큰 경우
                else {
                    for (String s : map.keySet()) {
                        boolean isContain=true;
                        for (int index = 0; index < s.length(); index++) {
                            if(!orders[c].contains(Character.toString(s.charAt(index)))) {
                                isContain=false;
                                break;
                            }
                        }
                        if(isContain) map.put(s, map.get(s) + 1);
                    }
                }

            }

            // 최댓값 뽑기
            int max=0;
            for (String s : map.keySet()) {
                max = Math.max(max, map.get(s));
            }

            // 최댓값이 2 이상에 대하여만
            // 최댓값이랑 같은 조합만 넣어주기
            if(max >=2){
                for (String s : map.keySet()) {
                    if(map.get(s)==max) arr.add(s);
                }
            }
            map.clear();
        }


        // 정렬한 뒤 return
        Collections.sort(arr);
        String[] answer= arr.toArray(new String[arr.size()]);
        return answer;
    }

    public static void combination(int n, int m) {
        if (n == m+1 ) {
            String menuComb = "";
            for (int i = 1; i < comb.length; i++) {
                menuComb += comb[i];
            }
            map.put(menuComb, 0);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if(!isVisited[i] && comb[n-1] < s.charAt(i)) {
                comb[n] = s.charAt(i);
                isVisited[i] = true;
                combination(n + 1, m);
                isVisited[i] = false;
            }
        }
    }
}
