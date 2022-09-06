package Programmers.CodingTestKit.스택_큐.프린터;
// 프로그래머스 - 프린터
import java.util.*;

public class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Position> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Position(priorities[i], i));
        }
        int count=0;
        while (!q.isEmpty()) {
            Position p = q.poll();
            boolean isExist = false;
            for (Position next : q) {
                if(p.priority < next.priority) {
                    q.add(p);
                    isExist=true;
                    break;
                }
            }
            if(!isExist) {
                count++;
                if(p.index==location) break;
            }
        }
        return count;

    }
}
class Position{
    int priority;
    int index;

    public Position(int priority, int index) {
        this.priority = priority;
        this.index = index;
    }
}
