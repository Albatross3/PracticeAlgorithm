package AlgorithmStudy.분할정복.P14601;

// 샤워실 바닥 깔기
// 분할 정복 문제
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int r, c; // 구멍의 위치
    static int[][] map;
    static int index = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 처음 정사각형 한 변의 길이
        int length = (int)Math.pow(2, k);
        int R = length - c;
        int C = r - 1;
        map = new int[length][length];
        map[R][C] = -1;

        tile_board_DC(map, length-1, R, C, 0, 0);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void tile_board_DC(int[][] board, int n, int x,int y, int startX,int startY) {
        // n==2 인 경우 트로미노 타일 붙임
        if (n == 1) {
            if (board[startX][startY] == 0) board[startX][startY] = index;
            if (board[startX][startY+1] == 0) board[startX][startY+1] = index;
            if (board[startX+1][startY] == 0) board[startX+1][startY] = index;
            if (board[startX+1][startY+1] == 0) board[startX+1][startY+1] = index;
            index++;
            return;
        }
        // board1~4 에서의 구멍 위치
        // x,y 구멍위치를 포함하지 않는 board 3개에 붙여야 함
        int x1 = (2*startX+n)/2, y1 = (2*startY+n)/2; // 1사분면
        int x2 = (2*startX+n)/2, y2 = (2*startY+n)/2+1; // 2사분면
        int x3 = (2*startX+n)/2+1, y3 = (2*startY+n)/2; // 3사분면
        int x4 = (2*startX+n)/2+1, y4 = (2*startY+n)/2+1; // 4사분면
        // board1 영역
        if (x >= startX && x <= startX+n / 2 && y >= startY && y <= startY+n / 2) {
            board[x2][y2] = index;
            board[x3][y3] = index;
            board[x4][y4] = index;
            index++;
            tile_board_DC(board, n / 2, x, y, startX, startY);
            tile_board_DC(board, n / 2, x2, y2, startX, startY+n/2+1);
            tile_board_DC(board, n / 2, x3, y3, startX+n/2+1, startY);
            tile_board_DC(board, n / 2, x4, y4, startX+n/2+1, startY+n/2+1);
        }
        // board2 영역
        else if (x >=startX && x <= startX+n/2 && y >startY+n/2 && y <= startY+n) {
            board[x1][y1] = index;
            board[x3][y3] = index;
            board[x4][y4] = index;
            index++;
            tile_board_DC(board, n / 2, x1, y1, startX, startY);
            tile_board_DC(board, n / 2, x, y, startX, startY+n/2+1);
            tile_board_DC(board, n / 2, x3, y3, startX+n/2+1, startY);
            tile_board_DC(board, n / 2, x4, y4, startX+n/2+1, startY+n/2+1);
        }
        // board3 영역
        else if (x > startX+n/2 && x <= startX+n && y >=startY && y <= startY+n/2) {
            board[x1][y1] = index;
            board[x2][y2] = index;
            board[x4][y4] = index;
            index++;
            tile_board_DC(board, n / 2, x1, y1, startX, startY);
            tile_board_DC(board, n / 2, x2, y2, startX, startY+n/2+1);
            tile_board_DC(board, n / 2, x, y, startX+n/2+1, startY);
            tile_board_DC(board, n / 2, x4, y4, startX+n / 2 + 1, startY+n / 2 + 1);
        }
        // board4 영역
        else {
            board[x1][y1] = index;
            board[x2][y2] = index;
            board[x3][y3] = index;
            index++;
            tile_board_DC(board, n / 2, x1, y1, startX, startY);
            tile_board_DC(board, n / 2, x2, y2, startX, startY+n/2+1);
            tile_board_DC(board, n / 2, x3, y3, startX+n/2+1, startY);
            tile_board_DC(board, n / 2, x, y, startX+n / 2 + 1, startY+n / 2 + 1);
        }
    }
}
