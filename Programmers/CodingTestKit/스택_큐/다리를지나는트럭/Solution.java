package Programmers.CodingTestKit.스택_큐.다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(2, 10, new int[]{7, 4, 5, 6}));
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Truck> wait = new LinkedList<>();
        Queue<Truck> passing = new LinkedList<>();
        Queue<Truck> past = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            wait.add(new Truck(truck_weights[i],0));
        }
        // 모두 지나갔을 때 탈출
        int passingWeightSum=0;
        int passingLength = 0;
        while (past.size()!= truck_weights.length) {

            // 추가했을 때 길이와 무게를 초과하지 않으면 다리로 옮겨주기
            if (wait.size()!=0 && passingWeightSum + wait.peek().weight <= weight && passingLength + 1 <= bridge_length) {
                Truck t = wait.poll();
                t.timePassed=-1;
                passing.add(t);
                passingWeightSum += t.weight;
                passingLength += 1;
            }
            time++;

            // 다리에 있는 truck들 시간 증가
            for (Truck t : passing) {
                t.timePassed += 1;
            }
            // passing에서의 맨 앞 time이 brige_time 인 경우 past로 옮기기
            if(passing.size()!=0 && passing.peek().timePassed==bridge_length){
                Truck t = passing.poll();
                past.add(t);
                passingWeightSum -= t.weight;
                passingLength -= 1;
                // 비었으니까 넣어줄 수 있으면 넣기
                if (wait.size()!=0 && passingWeightSum + wait.peek().weight <= weight && passingLength + 1 <= bridge_length) {
                    Truck tr = wait.poll();
                    passing.add(tr);
                    passingWeightSum += tr.weight;
                    passingLength += 1;
                }
            }
        }
        return time;
    }
}

class Truck {
    int weight;
    int timePassed;

    public Truck(int weight, int timePassed) {
        this.weight = weight;
        this.timePassed = timePassed;
    }
}
