package Programmers.KAKAO_BLIND_2021.L2_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}));
    }
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<ArrayList<String>,ArrayList<Integer>> m = new HashMap();
        String[] language = new String[]{"cpp", "java", "python", "-"};
        String[] job = new String[]{"backend", "frontend", "-"};
        String[] career = new String[]{"junior", "senior", "-"};
        String[] food = new String[]{"chicken", "pizza", "-"};
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
            for (int c = 0; c < 16; c++) {
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
            ArrayList<Integer> people = m.get(request);
            int count=0;
            for (int result : people) {
                if(result >= rT) count++;
            }
            answer[i]=count;
        }

        return answer;
    }
}
