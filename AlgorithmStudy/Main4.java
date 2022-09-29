package AlgorithmStudy;

// 백준 2309 -> 완전탐색으로도 쉽게 가능
// 백트래킹으로 조합 생성 후 해결함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main4 {
    static int[] comb;
    static int[] number;
    static boolean[] isVisited;
    static ArrayList<int[]> exclude = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dwarf = new int[9];
        for (int i = 0; i < 9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
        }

        // backtracking 준비
        number = new int[9];
        for (int i = 0; i < 9; i++) {
            number[i] = i+1;
        }
        comb = new int[3]; // [0,1,2]
        isVisited = new boolean[9];
        backtracking(1);

//        for (int[] arr : exclude) {
//            System.out.println(arr[0] + " " + arr[1]);
//        }

        // 제외할 조합을 제외하면서 100이 되는지 확인
        // 제외할 조합 찾기
        int exclude1 = 0;
        int exclude2 = 0;
        for (int[] arr : exclude) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                if (i == arr[0]-1 || i == arr[1]-1) continue;
                sum += dwarf[i];
            }
            if (sum == 100) {
                exclude1 = arr[0]-1;
                exclude2 = arr[1]-1;
                break;
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (i == exclude1 || i == exclude2) continue;
            answer.add(dwarf[i]);
        }
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for (int a : answer) {
            sb.append(a).append("\n");
        }
        System.out.println(sb);
    }

    // 1~9 중에서 2개 조합 뽑기 (1,2), (1,3) ... (1,9)
    public static void backtracking(int depth) {
        if (depth == 3) {
            int[] temp = new int[2];
            for (int i = 1; i < 3; i++) {
                temp[i - 1] = comb[i];
            }
            exclude.add(temp);
            return;
        }
        for (int i = 0; i < number.length; i++) {
            if (!isVisited[i] && comb[depth-1] < number[i]) { // 이부분에서 멈춤
                comb[depth] = number[i];
                isVisited[i] = true;
                backtracking(depth+1);
                isVisited[i] = false;
            }
        }
    }
}
