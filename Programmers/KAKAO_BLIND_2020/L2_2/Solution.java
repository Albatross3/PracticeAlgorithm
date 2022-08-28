package Programmers.KAKAO_BLIND_2020.L2_2;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("abcabcabcabcdededededede"));
    }
    public int solution(String s) {
        int r = s.length() / 2; // 반복횟수
        int min = s.length(); // 최소 길이를 찾아나갈 것임
        for (int i = 1; i <= r; i++) {
            int q = s.length() / i;
            String[] arr = new String[q+1];
            int index=0;
            for (int j = 0; j < q*i; j+=i) {
                arr[index++] = s.substring(j, j + i);
            }
            // 자르고 남은 나머지
            String remain = "";
            if(q*i!=s.length()) remain = s.substring(q * i);

            // 같은 것이 있으면 축약 하기
            arr[q] = "A";
            StringBuilder sb = new StringBuilder();
            int count=1;
            for (int j = 0; j < q; j++) {
                if (arr[j].equals(arr[j + 1])) {
                    count++;
                }else{
                    if(count==1) sb.append(arr[j]);
                    else sb.append(count).append(arr[j]);
                    count=1;
                }
            }
            sb.append(remain);
            String result = sb.toString();
            System.out.println(result);
            // 최종길이 비교
            min = Integer.min(min, result.length());
        }
        return min;
    }
}
