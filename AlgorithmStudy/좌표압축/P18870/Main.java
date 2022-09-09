package AlgorithmStudy.좌표압축.P18870;

// 입력의 크기를 줄일 때 활용하는 기법
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] origin;
    static int[] arr;
    static Map<Integer, Integer> m = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        origin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }
        // 중복 제거 및 index 부여
        arr = origin.clone();
        Arrays.sort(arr);
        int index=0;
        for (int i = 0; i < N; i++) {
            if(!m.containsKey(arr[i]))
                m.put(arr[i], index++);
        }

        for (int i = 0; i < N; i++) {
            sb.append(m.get(origin[i])).append(" ");
        }
        System.out.println(sb);
    }
}
