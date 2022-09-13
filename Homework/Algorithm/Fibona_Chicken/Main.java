package Homework.Algorithm.Fibona_Chicken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Main {
    static int P;
    static int[] fibonacci; // fibonacci[43]=433494437
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());
        fibonacci = new int[43+1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i]=fibonacci[i-1]+fibonacci[i-2];
        }
        // 인원이 1명일 때는 따로 처리
        int firstIndex = lowerBound(fibonacci, P);
        if(fibonacci[firstIndex]==P) {
            System.out.println(fibonacci[firstIndex-1]);
        }
        // firstIndex-1 부터 0까지 봐야함
        else if (fibonacci[firstIndex]>P) {
            int index = firstIndex - 1;
            while (index >= 0) {
                // 찾은 경우
                if (P > fibonacci[index]) {
                    P -= fibonacci[index];
                    sum += fibonacci[index - 1];
                    index -= 2;
                } else if (P < fibonacci[index]) {
                    index -= 1;
                } else {
                    sum += fibonacci[index - 1];
                    break;
                }
            }
            System.out.println(sum);
        }
    }

    public static int lowerBound(int[] arr, int target) {
        int start = 0, end = arr.length;
        while (start < end) {
            int middle = (start + end) / 2;
            if (arr[middle] < target) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return end;
    }
}
