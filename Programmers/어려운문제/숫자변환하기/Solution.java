package Programmers.어려운문제.숫자변환하기;
import java.util.*;
// bfs 로 최단 거리 찾는 풀이 -> 가지 3개씩 뻗어나가기
public class Solution {
    static Queue<Integer> queue = new LinkedList<>();
    static int[] visitCount;
    public int solution(int x, int y, int n) {
        // bfs 로 최단 거리 탐색하기 -> 3개의 가지치기가 계속해서 나옴
        visitCount = new int[y+1];
        queue.add(x);

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur == y) {
                return visitCount[cur];
            }
            if(cur + n <= y && visitCount[cur+n] == 0) {
                visitCount[cur+n] = visitCount[cur] + 1;
                queue.add(cur+n);
            }
            if(cur*2 <= y && visitCount[cur*2] == 0) {
                visitCount[cur*2] = visitCount[cur] + 1;
                queue.add(cur*2);
            }
            if(cur*3 <= y && visitCount[cur*3] == 0) {
                visitCount[cur*3] = visitCount[cur] + 1;
                queue.add(cur*3);
            }
        }

        return -1;
    }
}
