package AlgorithmStudy.빡구현.P16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        array = new int[N + 1][M + 1]; // 크기 1개씩 늘려서 만들기
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isShortCol=false;
        if (M < N) {
            isShortCol = true;
        }
        int max = Math.max(N, M);
        int min = Math.min(N, M); // (1,6) (2,5) (3,4) -> 1 ~ N/2

        // 짧은게 세로인 경우
        if (isShortCol) {
            for (int c = 1; c <= min / 2; c++) {
                Queue<Integer> queue = new LinkedList<>();
                // 맨 위쪽
                for (int i = c; i <= min + 1 - c; i++) {
                    queue.add(array[c][i]);
                }
                // 맨 오른쪽
                for (int i = c + 1; i <= max - c; i++) {
                    queue.add(array[i][min + 1 - c]);
                }
                // 맨 아래쪽
                for (int i = min + 1 - c; i >= c; i--) {
                    queue.add(array[max + 1 - c][i]);
                }
                // 맨 왼쪽
                for (int i = max - c; i >= c + 1; i--) {
                    queue.add(array[i][c]);
                }
                // 뽑아내고 나서 R 번 돌리기
                for (int i = 0; i < R; i++) {
                    int value = queue.poll();
                    queue.add(value);
                }
                // 다시 넣기
                for (int i = c; i <= min + 1 - c; i++) {
                    array[c][i] = queue.poll();
                }
                for (int i = c + 1; i <= max - c; i++) {
                    array[i][min + 1 - c] = queue.poll();
                }
                for (int i = min + 1 - c; i >= c; i--) {
                    array[max + 1 - c][i] = queue.poll();
                }
                for (int i = max - c; i >= c + 1; i--) {
                    array[i][c] = queue.poll();
                }
            }
        } else {
            for (int c = 1; c <= min / 2; c++) {
                Queue<Integer> queue = new LinkedList<>();
                // 맨 위쪽
                for (int i = c; i <= max + 1 - c; i++) {
                    queue.add(array[c][i]);
                }
                // 맨 오른쪽
                for (int i = c + 1; i <= min - c; i++) {
                    queue.add(array[i][max + 1 - c]);
                }
                // 맨 아래쪽
                for (int i = max + 1 - c; i >= c; i--) {
                    queue.add(array[min + 1 - c][i]);
                }
                // 맨 왼쪽
                for (int i = min - c; i >= c + 1; i--) {
                    queue.add(array[i][c]);
                }
                // 뽑아내고 나서 R 번 돌리기
                for (int i = 0; i < R; i++) {
                    int value = queue.poll();
                    queue.add(value);
                }
                // 다시 넣기
                for (int i = c; i <= max + 1 - c; i++) {
                    array[c][i]= queue.poll();
                }
                for (int i = c + 1; i <= min - c; i++) {
                    array[i][max + 1 - c]= queue.poll();
                }
                for (int i = max + 1 - c; i >= c; i--) {
                    array[min + 1 - c][i]= queue.poll();
                }
                for (int i = min - c; i >= c + 1; i--) {
                    array[i][c] = queue.poll();
                }
            }
        }
        System.out.println(printArray(array));
    }

    public static String printArray(int[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[0].length; j++) {
                sb.append(array[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
