package Programmers.KAKAO_BLIND_2019.L4;

// 무지의 먹방 라이브
// 정확성만 통과할 수 있는 코드
public class Solution {
    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println(s.solution(new int[]{1,1,1},3));
    }
    public int solution(int[] food_times, long k) {
        int index=0;
        int time=0;
        int rotateCount=0;
        while (true) {
            // 먹을 수 있는 경우
            if(food_times[index]!=0){
                time++;
                food_times[index++]--;
            }
            // 먹을 수 없는 경우
            else{
                index++;
                rotateCount++;
            }

            // 종료 조건 ->
            if(time==k+1 || rotateCount==food_times.length) break;

            // 마지막에 도달한 경우 0번으로
            if(index==food_times.length){
                index=0;
                rotateCount=0;
            }
        }
        if(rotateCount==food_times.length) return -1;
        if(index==0) return food_times.length;
        else return index;
    }
}
