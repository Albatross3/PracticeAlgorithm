package ThisIsCT.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 서울과기대-동국대 알고리즘 특강 4일차 3번 문제
// 이코테 책에는 없는 듯함
public class Main {
    static int x,y;
    static int[][] array;
    static int n;
    static int[][] startPoint;
    static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        array = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        n = Integer.parseInt(br.readLine());
        startPoint = new int[x - n + 1][y - n + 1];

        // 완전탐색으로 돌면서 체크하기
        for (int i = 0; i < x - n + 1; i++) {
            for (int j = 0; j < y - n + 1; j++) {
                check(i,j);
            }
        }

        // 출력 부분
        if(answer.isEmpty()) System.out.println(-1);
        else{
            Collections.sort(answer);
            System.out.println(answer.get(0));
        }
    }
    public static void check(int x,int y) {
        int count=0;
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if(array[i][j]==2) return;
                if(array[i][j]==1) count++;
            }
        }
        answer.add(count);
    }
}
