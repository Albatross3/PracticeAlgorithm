package Programmers.CodingTestKit.탐욕법.체육복;


// 안되는 case 가 여러번 생길 수 있기 때문에 
// 최적이 되는 상황을 고려하면서 코드를 짜야 한다
import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.solution(10, new int[]{3,4,5,7}, new int[]{3,5,9}));
    }
    public int solution(int n, int[] lost, int[] reserve) {
        int countSaved=0;
        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 여벌 체육복이 있는데 도난당한 경우
        // n이 최대 30이라 완전탐색 가능
        for(int i=0; i<lost.length; i++) {
            for(int j=0; j<reserve.length; j++){
                if(lost[i]==reserve[j]) {
                    lost[i]=-1;
                    reserve[j]=-1;
                    countSaved++;
                    break;
                }
            }
        }
        // 여벌 체육복이 있어 빌려줄 수 있는 경우
        for(int i=0; i<lost.length; i++) {
            for(int j=0; j<reserve.length; j++) {
                if(lost[i]-1 == reserve[j] || lost[i]+1==reserve[j]){
                    countSaved++;
                    reserve[j]=-1;
                    break;
                }
            }
        }

        return n-lost.length+countSaved;
    }
}
