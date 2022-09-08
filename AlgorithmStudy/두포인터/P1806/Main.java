package AlgorithmStudy.두포인터.P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        number = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        // 투 포인터 풀이
        // 종료 조건 -> point2 가 마지막 index 를 벗어나는 경우
        int point1 = 0, point2 = 0;
        int sum = number[0];
        int min = Integer.MAX_VALUE;
        while (true) {
            if (sum >= S) {
                min = Math.min(point2 - point1 + 1, min);
                sum -= number[point1++];
            } else {
                sum += number[++point2]; // 최종적으로 증가하는 부분을 잡기위해 크기를 +1 해서 초기 설정
            }
            // 종료 조건
            if(point2 == N) break;
        }
        if(min==Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}
