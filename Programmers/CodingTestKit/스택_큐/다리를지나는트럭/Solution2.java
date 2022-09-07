package Programmers.CodingTestKit.스택_큐.다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int weightSum=0;
        Queue<Integer> wait = new LinkedList<>();
        Queue<Integer> passing = new LinkedList<>();
        // 대기열 생성
        for (int i = 0; i < truck_weights.length; i++) {
            wait.add(truck_weights[i]);
        }

        while (!wait.isEmpty()) {
            // 큐 사이즈 < 다리 길이
            if (passing.size() < bridge_length) {
                // 감당 무게보다 작거나 같은 경우
                if (weightSum+wait.peek() <= weight) {
                    int t = wait.poll();
                    passing.add(t);
                    weightSum += t;
                }
                // 감당 무게보다 큰 경우 -> 0으로 채워준다
                else {
                    passing.add(0);
                }
                // 시간은 항상 증가
                time++;
            }
            // 큐 사이즈 = 다리 길이
            // 1개 나가고 1개 들어올 수 있으면 들어온다
            else if (passing.size() == bridge_length) {
                int t = passing.poll();
                weightSum -= t;
                // 대기열 비어있으면 break
                if(wait.isEmpty()) break;
                // 들어올 수 있으면 들어오기
                if (weightSum+wait.peek() <= weight) {
                    int tr = wait.poll();
                    passing.add(tr);
                    weightSum += tr;
                }
                // 감당 무게보다 큰 경우 -> 0으로 채워준다
                else {
                    passing.add(0);
                }
                // 시간은 항상 증가
                time++;

            }
        }
        return time + bridge_length;
    }
}
