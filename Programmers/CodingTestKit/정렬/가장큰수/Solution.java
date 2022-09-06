package Programmers.CodingTestKit.정렬.가장큰수;

// 프로그래머스
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        String[] arr = {"6", "10", "2"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        if(arr[0].equals("0")) return "0";
        else return sb.toString();
    }
}
