package Programmers;
// 초기 설계
// map 돌면서 p 나올때마다 확인
// 1. 동서남북으로 거리 1일때 p
// 2. 동서남북으로 거리 2일때 p & 거리 1일 때 o
// 3. 좌우대각선으로 거리 2일때 p & 사이에 o 하나라도 있으면
public class Solution {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[] px = {-1, 1, 1, -1};
    static int[] py = {1, 1, -1, -1};
    public int[] solution(String[][] places) {
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) {
            String[] map = places[i];
            if (isKeep(map)) result[i] = 1;
            else result[i] = 0;
        }
        return result;
    }
    public static boolean isKeep(String[] arr) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(arr[i].charAt(j)=='P'){
                    if(!isKeepDistance(arr,i,j))
                        return false;
                }
            }
        }
        return true;
    }

    public static boolean isKeepDistance(String[] map, int x, int y) {
        // 북,동,남,서
        for (int d = 0; d < 4; d++) {
            for (int s = 1; s <= 2; s++) {
                int tempX = x + s * dx[d];
                int tempY = y + s * dy[d];
                // map 안에 있는지
                if (tempX >= 0 && tempX < 5 && tempY >= 0 && tempY < 5) {
                    if(map[tempX].charAt(tempY)=='X') break;
                    else if(map[tempX].charAt(tempY)=='P') return false;
                }
            }
        }
        // 대각선
        for (int d = 0; d < 4; d++) {
            int tempX = x + px[d];
            int tempY = y + py[d];
            if (tempX >= 0 && tempX < 5 && tempY >= 0 && tempY < 5) {
                if(map[tempX].charAt(tempY)=='P' && (map[tempX].charAt(y)=='O'||map[x].charAt(tempY)=='O'))
                    return false;
            }
        }
        return true;
    }
}
