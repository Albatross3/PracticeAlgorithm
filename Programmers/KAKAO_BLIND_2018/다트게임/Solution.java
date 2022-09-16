package Programmers.KAKAO_BLIND_2018.다트게임;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("1D2S#10S"));
    }
    public int solution(String dartResult) {
        int[] answer = new int[3];
        int set=0;
        for (int i = 0; i < dartResult.length(); i++) {
            // 3 번의 iteration
            int index=0;
            if (dartResult.charAt(i) - '0' == 1) {
                if (dartResult.charAt(i + 1) - '0' == 0) {
                    answer[set]=10;
                    index=i+2;
                }
                else {
                    answer[set]=1;
                    index=i+1;
                }
            }
            else {
                answer[set] = dartResult.charAt(i)-'0';
                index = i+1;
            }

            while (true) {
                // 종료 조건
                if(index==dartResult.length()) break;
                if(dartResult.charAt(index) >= '0' && dartResult.charAt(index) <= '9' ) break;
                // 보너스 추가
                if(dartResult.charAt(index)=='S'||dartResult.charAt(index)=='D'||dartResult.charAt(index)=='T')
                    answer[set] = bonus(answer[set], dartResult.charAt(index));
                // 옵션 추가
                if (dartResult.charAt(index) == '*') {
                    if (set == 0) {
                        answer[0] = answer[0] * 2;
                    } else if (set==1) {
                        answer[0] = answer[0] * 2;
                        answer[1] = answer[1] * 2;
                    } else if (set==2) {
                        answer[1] = answer[1] * 2;
                        answer[2] = answer[2] * 2;
                    }
                }

                if (dartResult.charAt(index) == '#') {
                    answer[set] *= -1;
                }
                index++;
            }
            i = index - 1;
            set++;
        }
        return answer[0]+answer[1]+answer[2];
    }

    public static int bonus(int score, char area) {
        if(area=='S') score = score * 1;
        else if(area=='D') score = score * score;
        else if (area=='T') score = score * score * score;
        return score;
    }
}
