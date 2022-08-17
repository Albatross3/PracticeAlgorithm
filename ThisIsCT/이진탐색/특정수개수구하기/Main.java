package ThisIsCT.이진탐색.특정수개수구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진 탐색 활용
// lower bound, upper bound 구하는 이진 탐색 변형 문제
public class Main {
    static int N,X;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int left = binarySearch_left(num, X, 0, N - 1);
        int right = binarySearch_right(num, X, 0, N - 1);
        System.out.println(left);
        System.out.println(right);
        if(left==-1) System.out.println(-1);
        else System.out.println(right - left + 1);
    }

    public static int binarySearch_left(int[] arr, int target, int start, int end) {
        int middle = (start + end) / 2;
        if (start>end) return -1;
        if(arr[middle] < target ) return binarySearch_left(arr, target, middle + 1, end);
        else if (arr[middle] > target) return binarySearch_left(arr, target, start, middle - 1);
        else{
            if(middle==0) return 0;
            else{
                if(arr[middle-1]==target) return binarySearch_left(arr, target, start, middle - 1);
                else return middle;
            }
        }
    }

    public static int binarySearch_right(int[] arr, int target, int start, int end) {
        int middle = (start + end) / 2;
        if (start>end) return -1;
        if(arr[middle] < target ) return binarySearch_right(arr, target, middle + 1, end);
        else if (arr[middle] > target) return binarySearch_right(arr, target, start, middle - 1);
        else{
            if(middle+1==arr.length) return arr.length-1;
            else{
                if(arr[middle+1]==target) return binarySearch_right(arr, target, middle + 1, end);
                else return middle;
            }
        }
    }
}
