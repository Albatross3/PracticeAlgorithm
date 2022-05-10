package DAY08.P11660;

import java.io.*;
import java.util.StringTokenizer;

// 동적 계획법 1 - 구간 합 구하기5
// * 시간복잡도 O(N*N + M)
public class Main {
    static int N, M;
    static int[][] number;
    static int[][] DP;
    static int x1, y1, x2, y2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 숫자 배열로 입력 받기
        number = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                number[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 숫자 배열을 바탕으로 DP 배열 생성
        DP = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                DP[i][j] = DP[i - 1][j] + DP[i][j - 1] + number[i][j] - DP[i - 1][j - 1];
            }
        }

        // 입력 받고 출력
        int result;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            result = DP[x2][y2] - DP[x1 - 1][y2] - DP[x2][y1 - 1] + DP[x1 - 1][y1 - 1];
            bw.write(result+"\n");
        }
        bw.flush();
        bw.close();
    }
}


