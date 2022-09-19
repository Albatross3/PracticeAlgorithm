package Programmers.KAKAO_INTERN_2022.두큐합같게만들기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        System.out.println(s2.solution(new int[]{1,2,1,2},new int[]{1,10,1,2}));
    }
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> q1 = new LinkedList<>();
        long sum1 = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            q1.add(queue1[i]);
        }
        Queue<Integer> q2 = new LinkedList<>();
        long sum2 = 0;
        for (int i = 0; i < queue2.length; i++) {
            sum2 += queue2[i];
            q2.add(queue2[i]);
        }

        int task1=0;
        int task2=0;
        while (sum1 != sum2) {
            // 큐1 -> 큐2
            if (sum1 > sum2) {
                task1++;
                int num = q1.poll();
                sum1 -= num;
                q2.add(num);
                sum2 += num;
            }
            // 큐2 -> 큐1
            else if (sum1 < sum2) {
                task2++;
                int num = q2.poll();
                sum2 -= num;
                q1.add(num);
                sum1 += num;
            }
            answer++;

            // 종료 조건
            if (task1 + task2 == (queue1.length + queue2.length)*2) {
                return -1;
            }
        }


        return answer;
    }
}
