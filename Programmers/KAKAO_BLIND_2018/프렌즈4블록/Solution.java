package Programmers.KAKAO_BLIND_2018.프렌즈4블록;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// 8:00 - 8:30
//
public class Solution {

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        Position[][] map = new Position[m][n];
        for (int i = 0; i < m; i++) {
            String s = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = new Position(i, j, s.charAt(j));
            }
        }
        while (true) {
            // 1. 돌면서 2*2 블록의 좌표들을 모두 Set 에 저장 -> Set 크기 0이면 break
            Set<Position> set = new HashSet<>();
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    Position p1 = map[i][j];
                    Position p2 = map[i][j+1];
                    Position p3 = map[i+1][j];
                    Position p4 = map[i+1][j+1];

                    if (p1.character== p2.character && p2.character== p3.character && p3.character== p4.character ) {
                        if (p1.character != ' ') {
                            set.add(p1);
                            set.add(p2);
                            set.add(p3);
                            set.add(p4);
                        }
                    }
                }
            }
            if(set.size()==0) break;
            answer += set.size();
            // 2. Set에 해당하는 좌표 ' '로 바꾸면서 그 위의 블록이 내려오게 한다
            for (Position temp : set) {
                int r = temp.x;
                int c = temp.y;
                for (int i = r-1; i >= 0; i--) {
                    if (map[i][c].character != ' ') {
                        map[i + 1][c].character = map[i][c].character;
                        map[i][c].character = ' ';
                    }
                }
            }
        }

        return answer;
    }
}

class Position {
    int x, y;
    char character;

    public Position(int x, int y, char character) {
        this.x = x;
        this.y = y;
        this.character = character;
    }
}
