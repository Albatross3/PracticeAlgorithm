package AlgorithmStudy.그래프탐색.P14500;

// DFS 활용 문제
// 'ㅗ' 모양을 찾는 더 쉬운 방법 있을 듯
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static int MAX = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // map 돌면서 dfs 돌리기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, map[i][j]);
                isVisited[i][j] = false;
            }
        }
        // map 돌면서 'ㅗ'모양 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ArrayList<Cor> temp = new ArrayList<>();
                for (int d = 0; d < 4; d++) {
                    int tempX = i + dx[d];
                    int tempY = j + dy[d];
                    if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < M) {
                        temp.add(new Cor(tempX, tempY, map[tempX][tempY]));
                    }
                }
                int result = 0;
                // 크기가 2인 경우 -> 넘어감
                if (temp.size()==2) continue;
                // 크기 3인 경우
                if (temp.size() == 3) {
                    result = map[i][j] + map[temp.get(0).x][temp.get(0).y] + map[temp.get(1).x][temp.get(1).y] + map[temp.get(2).x][temp.get(2).y];
                } else if (temp.size() == 4) {
                    result = Math.max(result, map[i][j] + map[temp.get(0).x][temp.get(0).y] + map[temp.get(1).x][temp.get(1).y] + map[temp.get(2).x][temp.get(2).y]);
                    result = Math.max(result, map[i][j] + map[temp.get(1).x][temp.get(1).y] + map[temp.get(2).x][temp.get(2).y] + map[temp.get(3).x][temp.get(3).y]);
                    result = Math.max(result, map[i][j] + map[temp.get(2).x][temp.get(2).y] + map[temp.get(3).x][temp.get(3).y] + map[temp.get(0).x][temp.get(0).y]);
                    result = Math.max(result, map[i][j] + map[temp.get(3).x][temp.get(3).y] + map[temp.get(0).x][temp.get(0).y] + map[temp.get(1).x][temp.get(1).y]);

                }
                MAX = Math.max(MAX, result);
            }
        }
        System.out.println(MAX);
    }

    public static void dfs(int x, int y, int depth,int sum) {
        // 1. 체크인
        isVisited[x][y] = true;
        // 2. 목적지인가?
        if (depth == 3) {
            MAX = Math.max(MAX, sum);
            return;
        }
        // 3. 연결된 곳 순회
        for (int d = 0; d < 4; d++) {
            int tempX = x + dx[d];
            int tempY = y + dy[d];
            // 4. 갈 수 있는가? -> map 안에 존재 & 방문하지 않은 곳
            if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < M && !isVisited[tempX][tempY]) {
                // 5. 간다
                dfs(tempX, tempY, depth + 1, sum + map[tempX][tempY]);
                // 6. 체크아웃
                isVisited[tempX][tempY] = false;
            }
        }


    }


}

class Cor implements Comparable<Cor> {
    int x, y;
    int value;

    public Cor(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public int compareTo(Cor o) {
        return o.value - this.value;
    }
}
