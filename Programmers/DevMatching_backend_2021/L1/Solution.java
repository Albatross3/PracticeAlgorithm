package Programmers.DevMatching_backend_2021.L1;

// 로또의 최고순위와 최저순위
// 배열 2개가 주어지고 배열에서 공통으로 몇개가 있는지 찾는 형태 (정렬은 안되어 있음)
// map 사용하면 O(n)에 가능할듯
public class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int numAnswer=0;
        for(int i=0; i<win_nums.length; i++){
            for(int j=0; j<lottos.length; j++){
                if(lottos[j]==win_nums[i]) numAnswer++;
            }
        }

        int numZero=0;
        for(int i=0; i<lottos.length; i++){
            if(lottos[i]==0) numZero++;
        }
        answer[0]=getRank(numAnswer+numZero);
        answer[1]=getRank(numAnswer);
        return answer;
    }
    public static int getRank(int numCorrection){
        int rank;
        switch (numCorrection){
            case 6:
                rank=1;
                break;
            case 5:
                rank=2;
                break;
            case 4:
                rank=3;
                break;
            case 3:
                rank=4;
                break;
            case 2:
                rank=5;
                break;
            default:
                rank=6;
        }
        return rank;
    }
}
