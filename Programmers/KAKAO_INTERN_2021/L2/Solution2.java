package Programmers.KAKAO_INTERN_2021.L2;

import Programmers.KAKAO_INTERN_2021.L2.Position;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
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
        Queue<Position> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[5][5];
        q.add(new Position(x, y, 'P',0));
        isVisited[x][y]=true;

        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내기
            Position p = q.poll();
            // 2. 목적지 인가?
            if (p.type == 'P' && (p.dist == 1 || p.dist == 2)) {
                return false;
            }
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = p.x + dx[d];
                int tempY = p.y + dy[d];
                int dist = Math.abs(x - tempX) + Math.abs(y - tempY);
                // 4. 갈 수 있는가?
                if (tempX >= 0 && tempX < 5 && tempY >= 0 && tempY < 5) {
                    if ((map[tempX].charAt(tempY) == 'O' || map[tempX].charAt(tempY) == 'P' )&& !isVisited[tempX][tempY] && dist<=2) {
                        // 5. 간다
                        isVisited[tempX][tempY]=true;
                        // 6. 큐에 넣기
                        q.add(new Position(tempX, tempY, map[tempX].charAt(tempY), p.dist + 1));
                    }
                }
            }
        }
        return true;
    }
}
class Position{
    int x,y;
    char type;
    int dist;

    public Position(int x, int y, char type,int dist) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.dist = dist;
    }
}
