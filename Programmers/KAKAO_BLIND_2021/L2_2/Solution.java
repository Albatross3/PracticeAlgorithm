package Programmers.KAKAO_BLIND_2021.L2_2;
/*
<초기 설계>
1. 지원자마다 만들 수 있는 모든 조합을 key로서 map에 넣기
2. query를 돌면서 해당 조합의 value(int 배열) 가져오기
3. 점수 x 이상인 개수 세서 추가
-> 메모리 초과..
최선의 설계인것일까..? -> 효율성 2문제 시간 초과 (2/4)
--> info 가 1개 조합에 대한 정보만 있는 경우, query 도 그 조합에 대해서 요구하면
--> 100000 * 50000 으로 시간초과 날 수 밖에 없다

<두 번째 설계>
시간 초과 나는 부분을 해결하기 위해 x 점수 이상의 개수를 구하는 것을 이진 탐색으로 구현하려한다.
1. 각 조합의 점수들 정렬 필요
2. lower bound 찾는 이진탐색
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s=new Solution();
        int[] a=s.solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
        System.out.println(Arrays.toString(a));
    }
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<ArrayList<String>,ArrayList<Integer>> m = new HashMap();
        String[] language = new String[]{"-","cpp", "java", "python"};
        String[] job = new String[]{"-","backend", "frontend"};
        String[] career = new String[]{"-","junior", "senior"};
        String[] food = new String[]{"-","chicken", "pizza"};
        for (int i = 0; i < language.length; i++) {
            for (int j = 0; j < job.length; j++) {
                for (int k = 0; k < career.length; k++) {
                    for (int w = 0; w < food.length; w++) {
                        ArrayList<String> comb = new ArrayList<>();
                        comb.add(language[i]);
                        comb.add(job[j]);
                        comb.add(career[k]);
                        comb.add(food[w]);
                        m.put(comb, new ArrayList<>());
                    }
                }
            }
        }
        // 지원자마다 만들 수 있는 모든 조합을 map 에 key 로서 넣기
        for(int i=0; i< info.length; i++){
            StringTokenizer st=new StringTokenizer(info[i]);
            String l = st.nextToken();
            String j = st.nextToken();
            String e = st.nextToken();
            String f = st.nextToken();
            int test = Integer.parseInt(st.nextToken());
            for (int comb = 0; comb < 16; comb++) {
                int c=comb;
                ArrayList<String> arr = new ArrayList<>();
                if(c/8==0) arr.add("-");
                else arr.add(l);
                c=c%8;
                if(c/4==0) arr.add("-");
                else arr.add(j);
                c=c%4;
                if(c/2==0) arr.add("-");
                else arr.add(e);
                c=c%2;
                if(c==0) arr.add("-");
                else arr.add(f);

                // 키를 map 에 추가
                m.get(arr).add(test);
            }
        }
        // map 에서 각 조합의 점수들을 정렬하기
        for (ArrayList<String> arr : m.keySet()) {
            Collections.sort(m.get(arr));
        }
        // query 를 돌면서 해당 조합의 배열 가져오기
        for (int i = 0; i < query.length; i++) {
            String[] sentence = query[i].split(" ");
            String rL = sentence[0];
            String rJ = sentence[2];
            String rE = sentence[4];
            String rF = sentence[6];
            int rT = Integer.parseInt(sentence[7]);
            ArrayList<String> request = new ArrayList<>();
            request.add(rL); request.add(rJ); request.add(rE); request.add(rF);
            // 완탐 -> 이진 탐색
            answer[i]=findApplicant(m.get(request),rT);
        }

        return answer;
    }
    public static int findApplicant(ArrayList<Integer> arr, int target){
        // 해당 target 값을 찾는 것이 아닌
        // target 값보다 작거나 같은 lower bound 찾기
        int start = 0, end = arr.size();
        while (start < end) {
            int middle=(start+end)/2;
            if (arr.get(middle) >= target) {
                end=middle;
            }else {
                start = middle + 1;
            }
        }
        return arr.size() - start;
    }
}
