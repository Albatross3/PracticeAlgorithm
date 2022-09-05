package Programmers.KAKAO_BLIND_2018.L1;

public class Solution2 {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(31|14));
    }
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            // 어떤 정수 값을 binary 이면서 String 으로 변환해준다
            answer[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
        }
        for (int i = 0; i < n; i++) {
            // String 의 형식(예를 들어 길이, 오른쪽 정렬, 왼쪽 정렬 등)을 결정하는 함수
            answer[i] = String.format("%" + n + "s", answer[i]);
            answer[i] = answer[i].replaceAll("1", "#");
            answer[i] = answer[i].replaceAll("0", " ");
        }
        return answer;
    }
}
