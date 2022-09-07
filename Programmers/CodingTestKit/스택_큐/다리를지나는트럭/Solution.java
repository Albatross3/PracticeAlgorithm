package Programmers.CodingTestKit.스택_큐.다리를지나는트럭;
// 반복문에서의 논리적 흐름을 순서에 맞게 설계 해야했음
// 탈출 -> 들어오기 순서로
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int weightSum = 0;
        Queue<Truck> wait = new LinkedList<>();
        Queue<Truck> passing = new LinkedList<>();
        // 대기열 초기화
        for (int i = 0; i < truck_weights.length; i++) {
            wait.add(new Truck(truck_weights[i], 0));
        }

        while (!wait.isEmpty() || !passing.isEmpty()) {
            time++;
            // passing 비어있는 경우
            if (passing.isEmpty()) {
                Truck t = wait.poll();
                passing.add(t);
                weightSum += t.weight;
                continue;
            }
            // 지나가는 큐에 들어있는 트럭 모두 시간 증가
            for (Truck t : passing) {
                t.increaseTime();
            }

            // 길이 만큼의 시간 지나면 탈출
            if (passing.peek().timePassed == bridge_length) {
                Truck t = passing.poll();
                weightSum -= t.weight;
            }
            // 대기열에서 트럭 추가 -> 대기열 비어있지 않은 경우 & 길이 <= bridge_length & 무게 <= weight
            if (!wait.isEmpty() && passing.size() + 1 <= bridge_length && weightSum + wait.peek().weight <= weight) {
                Truck t = wait.poll();
                passing.add(t);
                weightSum += t.weight;
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

    public void increaseTime() {
        timePassed++;
    }
}
