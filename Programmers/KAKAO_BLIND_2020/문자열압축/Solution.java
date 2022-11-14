package Programmers.KAKAO_BLIND_2020.문자열압축;

public class Solution {
    public static void main(String[] args)  {
        Solution solution = new Solution();
        solution.solution("ababcdcdababcdcd");
    }
    public int solution(String s) {
        int R = s.length() / 2;
        int min = s.length();
        for (int r = 1;  r <= R; r++) {
            int length = s.length() / r;
            String[] arr = new String[length + 1];
            // 1. 배열에 분리
            int index = 0;
            for (int i = 0; i < arr.length-1; i++) {
                arr[i] = s.substring(index, index + r);
                index += r;
            }
            // 마지막 비교를 위한 문자열 추가
            arr[arr.length - 1] = "";
            // 2. 문자열 압축
            int count=1;
            String prev = arr[0];
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < arr.length; i++) {
                if (prev.equals(arr[i])) {
                    count++;
                } else {
                    if (count == 1) {
                        sb.append(prev);
                    }
                    else{
                        sb.append(count).append(prev);
                    }
                    prev = arr[i];
                    count = 1;
                }
            }
            // 3. 남겨진 문자열 추가
            sb.append(s.substring(length * r));

            // 4. 최솟값 구하기
            min = Math.min(min, sb.toString().length());
        }
        return min;
    }
}
