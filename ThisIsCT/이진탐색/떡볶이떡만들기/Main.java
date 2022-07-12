package ThisIsCT.이진탐색.떡볶이떡만들기;

// 이진 탐색 응용
// 요구되는 M보다 길거나 같은 길이를 반환하기 위해 maxH를 따로 설정
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] riceCake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        riceCake = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            riceCake[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(riceCake);
        int max = riceCake[N - 1];

        System.out.println(binarySearch(riceCake,M,0,max));

    }

    public static int binarySearch(int[] arr, int target, int start ,int end) {
        int maxH=0;
        while (start <= end) {
            int middle = (start + end) / 2;
            int remain = calculate(arr, middle);
            if(remain < M) end = middle - 1;
            else if (remain > M) {
                start = middle + 1;
                maxH=middle;
            }
            else return  middle;
        }
        return maxH;
    }


    public static int calculate(int[] arr, int height) {
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > height) sum += arr[i] - height;
        }
        return sum;
    }
}
