package Homework;

import java.util.Arrays;

// Java 에서 배열을 받는 method 는 메모리의 주소값을 가져오기에
// 메소드에서의 변경사항이 배열에 영향을 줄 수 있다
public class Main {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        System.out.println(Arrays.toString(arr));
        deleteArr(arr,2);
        System.out.println(Arrays.toString(arr));
    }

    public static void deleteArr(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==target) {
                arr[i]=0;
            }
        }
    }
}
