package SAMSUNG;

public class Practice {
    static int N = 2;
    static int[][] map;
    public static void main(String[] args) {
        map = new int[N][N];



    }

    // 시계 방향 회전 모듈화
    static void rotateClockwise90() {
        int[][] temp = new int[N][N];

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                temp[j][N - 1 - i] = map[i][j];
            }
        }

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    // 반시계 방향 회전 모듈화
    static void rotateCounterClockwise90() {
        int[][] temp = new int[N][N];

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - 1 - j][i] = map[i][j];
            }
        }

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    // 특정 경로 따라서 이동하는 법 모듈화
    // 경로들 저장해두기
    static void move() {

    }

    // 빈 칸 생겼을 때 채워주는 법 모듈화
    // 빈 칸 생겼을 때 현재 위치와 바꾸어주기



}
