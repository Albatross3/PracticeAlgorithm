package Homework.Algorithm.합병정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        // 입력받은 array 를 합병 정렬
        mergeSort(array, 0, array.length-1);
        for (int i = 0; i < n; i++) {
            sb.append(array[i]).append("\n");
        }

        System.out.println(sb);
    }

    public static void mergeSort(int[] arr, int left, int right) {
        int mid;
        // 분할 하였을 때 index 가 같아지는 순간까지 나눔
        // index 가 같아지는 순간 합병을 하고 다시 위에서 합병을 하는 과정
        if (left < right) {
            mid = (left + right) / 2; // 중앙 index 구하기
            mergeSort(arr, left, mid); // 중앙을 기준으로 왼쪽(중앙 포함) 분할
            mergeSort(arr, mid + 1, right); // 중앙을 기준으로 오른쪽(중앙 포함 x) 분할
            merge(arr, left, mid, right); // 합병하는 과정 (왼쪽,오른쪽 쪼갠 것을 합친다)
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // 분할된 왼쪽 배열에서 시작점 변수
        int leftIndex = left;
        // 분할된 오른쪽 배열에서 시작점 변수
        int rightIndex = mid+1;
        // 정렬된 데이터가 저장될 인덱스
        int sortedIndex = left;
        // 정렬된 데이터를 임시로 저장할 곳
        int[] tempSortedArray = new int[right + 1];
        
        
        // 분할된 왼쪽 배열의 인덱스가 mid 까지 온 경우 왼쪽 정렬 완료
        // 분할된 오른쪽 배열의 인덱스가 right 까지 온 경우 오른쪽 정렬 완료
        // 즉, 왼쪽 또는 오른쪽 둘 중 하나라도 정렬이 완료된 경우 반복문을 빠져나감
        while (leftIndex <= mid && rightIndex <= right) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                tempSortedArray[sortedIndex++] = arr[leftIndex++];
            } else {
                tempSortedArray[sortedIndex++] = arr[rightIndex++];
            }
        }
        
        // 왼쪽 또는 오른쪽 배열에 남아있는 데이터를 옮기는 과정
        
        // 왼쪽이 다 정렬된 경우 -> 남아있는 오른쪽 배열의 데이터를 전부 옮긴다
        if (leftIndex > mid) {
            for (int i = rightIndex; i <= right; i++) {
                tempSortedArray[sortedIndex++] = arr[i];
            }
        }
        // 왼쪽이 다 정렬되지 않은 경우 -> 남아있는 왼쪽 배열의 데이터를 전부 옮긴다
        else {
            for (int i = leftIndex; i <= mid; i++) {
                tempSortedArray[sortedIndex++] = arr[i];
            }
        }
        
        // 최종적으로 기존의 배열에 임시로 생성한 배열에 있는 데이터를 옮겨준다
        for (int i = left; i <= right; i++) {
            arr[i] = tempSortedArray[i];
        }
    }
}
