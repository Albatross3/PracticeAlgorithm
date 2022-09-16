package Programmers.KAKAO_BLIND_2018.비밀지도만들기;

public class Solution {
    public static void main(String[] args) {
        System.out.println((int) Math.pow(2, 0));
        System.out.println(changeToBinary(9,5));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(5|9);
    }
    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        String[] secretMap = new String[n];
        for (int i = 0; i < n; i++) {
            String temp1 = changeToBinary(arr1[i], n);
            System.out.println(temp1);
            String temp2 = changeToBinary(arr2[i], n);
            for (int j = 0; j < n; j++) {
                map1[i][j] = temp1.charAt(j) - '0';
                map2[i][j] = temp2.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map1[i][j]);
            }
            System.out.println();
        }
        // 비밀 지도 만들기
        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if(map1[i][j]==1 || map2[i][j]==1) s.append("#");
                else s.append(" ");
            }
            secretMap[i] = s.toString();
        }
        return secretMap;
    }

    public static String changeToBinary(int target, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >= 0; i--) {
            int p = (int) Math.pow(2, i);
            int q = target / p;
            sb.append(q);
            target = target % p;
        }
        return sb.toString();
    }
}
