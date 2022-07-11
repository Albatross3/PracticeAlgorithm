package ThisIsCT.이진탐색.부품찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] menu;
    static int[] require;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        menu = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            menu[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        require = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            require[i] = Integer.parseInt(st.nextToken());
        }

        // 이진 탐색을 위한 정렬
        Arrays.sort(menu);
        for (int i = 0; i < M; i++) {
            if(binarySearch(menu,require[i],0,N-1 )) sb.append("yes").append(" ");
            else sb.append("no").append(" ");
        }
        System.out.println(sb);
    }

    public static boolean binarySearch(int[] arr, int target, int start, int end) {
        int middle = (start + end) / 2;
        // 못찾은 경우
        if(start > end) return false;

        if(arr[middle] < target ) return binarySearch(arr, target, middle + 1, end);
        else if(arr[middle] >target) return binarySearch(arr, target, start, middle - 1);
        else return true;
    }
}
