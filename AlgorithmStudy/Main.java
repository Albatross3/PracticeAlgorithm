package AlgorithmStudy;

// String 초기화를 하려면 String s="" 를 해주어야 한다
// 안해 주면 garbage 값 들어감
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String target = "";
    static boolean[] isVisited;
    static int[] combination;
    static ArrayList<String> arr = new ArrayList<>();
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            target += Integer.parseInt(st.nextToken());
            target += " ";
        }

        isVisited = new boolean[N + 1];
        combination = new int[N + 1];
        backtracking(1);

        int index=0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(target)) {
                index=i;
                break;
            }
        }

        if (index == arr.size() - 1) {
            System.out.println(-1);
        }else {
            System.out.println(arr.get(index+1));
        }
    }

    public static void backtracking(int depth) {
        if (depth == N + 1) {
            sb = new StringBuilder();
            for (int i = 1; i < combination.length; i++) {
                sb.append(combination[i]).append(" ");
            }
            arr.add(sb.toString());
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i]) {
                combination[depth] = i;
                isVisited[i] = true;
                backtracking(depth + 1);
                isVisited[i] = false;
            }

        }
    }

}
