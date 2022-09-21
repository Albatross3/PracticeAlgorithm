package Programmers;

public class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                int[] col = getColumn(arr2, j);
                answer[i][j] = multiply(arr1[i], col);
            }
        }
        return answer;
    }

    public static int multiply(int[] temp1, int[] temp2) {
        int result=0;
        for (int i = 0; i < temp1.length; i++) {
            result += temp1[i] * temp2[i];
        }
        return result;
    }

    public static int[] getColumn(int[][] arr,int c) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i][c];
        }
        return temp;
    }
}
