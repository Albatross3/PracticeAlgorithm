package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {
    static int N, M;
    static int[] number;
    static boolean[] isVisited;
    static int[] comb;
    static Set<ArrayList<Integer>> set = new LinkedHashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // target 깊이

        isVisited = new boolean[N];
        number = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(number);
        comb = new int[M];
        back(0);

        for (ArrayList<Integer> arr : set) {
            for (int num : arr) {
                sb.append(num+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void back(int depth) {
        if (depth == M) {
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < comb.length; i++) {
                arr.add(comb[i]);
            }
            set.add(arr);
            return;
        }
        for (int i = 0; i < number.length; i++) {
            if (!isVisited[i]) {
                comb[depth] = number[i];
                isVisited[i] = true;
                back(depth + 1);
                isVisited[i] = false;
            }
        }
    }

}

