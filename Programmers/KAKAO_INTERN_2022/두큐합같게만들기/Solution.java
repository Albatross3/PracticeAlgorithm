package Programmers.KAKAO_INTERN_2022.두큐합같게만들기;

import java.util.LinkedList;
import java.util.Queue;

// 최솟값을 구하는 문제로서 greedy 방식을 이용
// 종료 조건을 잘 모르겠음
public class Solution {
    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println(s.solution(new int[]{3},new int[]{6}));
    }
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1=0;
        for(int num:queue1) {
            q1.add(num);
            sum1+=num;
        }
        long sum2=0;
        for(int num:queue2) {
            q2.add(num);
            sum2+=num;
        }
        long origin1=sum1;
        long origin2=sum2;

        int result=0;
        while(true){
            if(sum1==sum2) return result;
            // q1 의 합이 q2 보다 클 때
            else if(sum1 > sum2) {
                int num=q1.poll();
                q2.add(num);
                sum1-=num;
                sum2+=num;
                result++;
            }
            // q2 의 합이 q1 보다 클 때
            else {
                int num=q2.poll();
                q1.add(num);
                sum2-=num;
                sum1+=num;
                result++;
            }
            // 종료 조건
            if(q1.isEmpty() || q2.isEmpty() || (sum1==origin2 && sum2==origin1)) return -1;
        }
    }
}
