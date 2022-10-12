package LeetCode.WordSearch;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.exist(new char[][]{{'C','A','A'},{'A','A','A'},{'B','C','D'}},"AAB"));
    }
    static String WORD;
    static int R, C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] isVisited;
    static boolean isFind;
    public boolean exist(char[][] board, String word) {
        WORD = word;
        R= board.length;
        C = board[0].length;
        isFind = false;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if (board[i][j] == WORD.charAt(0)) {
                    isVisited = new boolean[R][C];
                    dfs(board, i, j, 1);
                }
                if (isFind) break;
            }
        }
        return isFind;
    }

    public static void dfs(char[][] board, int x, int y, int depth) {
        // 1. 체크인
        isVisited[x][y] = true;
        // 2. 목적지인가? -> 깊이에 도달 하였는가?
        if (depth == WORD.length()) {
            isFind = true;
            return;
        }
        // 3. 연결된 곳 순회
        for (int d = 0; d < 4; d++) {
            int tempX = x + dx[d];
            int tempY = y + dy[d];
            // 4. 갈 수 있는가? -> map 안에 존재 & word 의 다음위치랑 일치 & 방문하지 않은 타일
            if (tempX >= 0 && tempX < R && tempY >= 0 && tempY < C && !isVisited[tempX][tempY] && board[tempX][tempY] == WORD.charAt(depth)) {
                // 5. 간다
                dfs(board, tempX, tempY, depth + 1);
                // 6. 체크아웃
                isVisited[tempX][tempY]=false;
            }
        }
    }
}
