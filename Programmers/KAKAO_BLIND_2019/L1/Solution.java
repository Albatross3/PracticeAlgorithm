package Programmers.KAKAO_BLIND_2019.L1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) throws IOException {
        Solution p = new Solution();
        System.out.println(p.solution(5,new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
    }
    public int[] solution(int N, int[] stages) {
        int[] status = new int[N + 2];
        for (int i = 0; i < stages.length; i++) {
            status[stages[i]]++;
        }
        ArrayList<Fail> arr = new ArrayList();
        int people = stages.length;
        double failure;
        for (int i = 1; i <= N; i++) {
            if(people==0) failure=0;
            else failure = (double)status[i] / people;
            arr.add(new Fail(i, failure));
            people -= status[i];
        }
        Collections.sort(arr, new Comparator<Fail>() {
            @Override
            public int compare(Fail o1, Fail o2) {
                if(o1.failureRate < o2.failureRate) return 1;
                else if(o1.failureRate==o2.failureRate) return o1.stage - o2.stage;
                else return -1;
            }
        });

        int[] answer=new int[N];
        int i=0;
        for (Fail f : arr) {
            answer[i++] = f.stage;
        }
        return answer;
    }
}
class Fail{
    int stage;
    double failureRate;

    public Fail(int stage, double failureRate) {
        this.stage = stage;
        this.failureRate = failureRate;
    }
}
