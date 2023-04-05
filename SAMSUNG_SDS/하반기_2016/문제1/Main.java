package SAMSUNG_SDS.하반기_2016.문제1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x, y, k;
    static int[][] board;
    // 1 2 3 4 -> 동 서 북 남
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static int[][] cube;
    static int[] surface;

    static int topOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 정육면체 전개도 설정
        cube = new int[3][3];
        cube[1][1] = 6;
        cube[0][1] = 2; cube[2][1] = 5;
        cube[1][0] = 4; cube[2][0] = 3;
        topOrder = 1;

        // surface 배열 -> order 에 따른 숫자 들어있음
        surface = new int[7];

        // 주사위 굴리는 동안
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());
            if (!canMove(x, y, dir)) continue;
            // 새로운 위치 update
            x = x + dx[dir];
            y = y + dy[dir];

            // 주사위 바닥면의 order 구하기
            int bottomOrder = cube[1 + dx[dir]][1 + dy[dir]];

            // 바닥 0 이면 <- 주사위
            // 바닥 0 이 아니면 -> 주사위 + 바닥 0 됨
            if (board[x][y] == 0) {
                board[x][y] = surface[bottomOrder];
            } else {
                surface[bottomOrder] = board[x][y];
                board[x][y] = 0;
            }
            // 전개도 변화
            change(dir);

            // 맨 위의 값 출력
            sb.append(surface[topOrder]).append("\n");
        }
        System.out.println(sb);
    }

    public static void change(int direction) {
        int temp;
        switch (direction) {
            case 1 :
                temp = cube[1][0];
                cube[1][0] = cube[1][1];
                cube[1][1] = cube[1][2];
                cube[1][2] = topOrder;
                topOrder = temp;
                break;
            case 2:
                temp = cube[1][2];
                cube[1][2] = cube[1][1];
                cube[1][1] = cube[1][0];
                cube[1][0] = topOrder;
                topOrder = temp;
                break;
            case 3:
                temp = cube[2][1];
                cube[2][1] = cube[1][1];
                cube[1][1] = cube[0][1];
                cube[0][1] = topOrder;
                topOrder = temp;

                break;
            case 4:
                temp = cube[0][1];
                cube[0][1] = cube[1][1];
                cube[1][1] = cube[2][1];
                cube[2][1] = topOrder;
                topOrder = temp;
                break;
        }
    }

    public static boolean canMove(int currentX, int currentY, int direction) {
        int nextX = currentX + dx[direction];
        int nextY = currentY + dy[direction];
        return nextX >= 0 && nextX < n && nextY >= 0 && nextY < m;
    }


}

