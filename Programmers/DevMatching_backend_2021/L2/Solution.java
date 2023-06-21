package Programmers.DevMatching_backend_2021.L2;

import java.util.*;
class Solution {
    static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        // map 생성
        map = new int[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                map[i][j] = i*columns + j+1;
            }
        }

        int[] answer = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            answer[i] = rotate(queries[i][0],queries[i][1],queries[i][2],queries[i][3]);
        }

        return answer;
    }
    public static int rotate(int x1, int y1, int x2, int y2) {
        Queue<Number> q = new LinkedList<>();
        // 오른
        for(int i=y1; i<=y2; i++) {
            q.add(new Number(map[x1-1][i-1],x1,i));
        }
        // 아래
        for(int i=x1+1; i<=x2; i++) {
            q.add(new Number(map[i-1][y2-1],i, y2));
        }
        // 왼
        for(int i=y2-1; i>=y1; i--) {
            q.add(new Number(map[x2-1][i-1],x2, i));
        }
        // 위
        for(int i=x2-1; i>x1; i--) {
            q.add(new Number(map[i-1][y1-1],i, y1));
        }

        // 한 칸 옮기기
        Number num = q.poll();
        int n = num.number;
        q.add(num);

        // 하나씩 빼면서 map 변경
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Number temp = q.poll();
            map[temp.x-1][temp.y-1]=n;
            n = temp.number;
            min = Math.min(min, n);
        }
        return min;
    }
}
class Number {
    int number;
    int x;
    int y;

    public Number(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }
}
