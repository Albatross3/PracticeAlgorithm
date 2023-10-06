package SAMSUNG.SW_2022_상반기.오전.예술성;

// 예술성
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Cor[][] picture;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] isVisited;

    static int startArtPoint;
    static int artPointAfterRotation1;
    static int artPointAfterRotation2;
    static int artPointAfterRotation3;

    static class Cor {
        int x;
        int y;
        int number;
        int groupNumber;

        public Cor(int x, int y, int number, int groupNumber) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.groupNumber = groupNumber;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        picture = new Cor[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                picture[i][j] = new Cor(i, j, Integer.parseInt(st.nextToken()), 0);
            }
        }

        // 초기 예술 점수
        startArtPoint = getArtPoint(picture);

        // 1회전 후 예술 점수
        Cor[][] pictureAfterRotation1 = rotate(picture);
        artPointAfterRotation1 = getArtPoint(pictureAfterRotation1);
        // 2회전 후 예술 점수
        Cor[][] pictureAfterRotation2 = rotate(pictureAfterRotation1);
        artPointAfterRotation2 = getArtPoint(pictureAfterRotation2);
        // 3회전 후 예술 점수
        Cor[][] pictureAfterRotation3 = rotate(pictureAfterRotation2);
        artPointAfterRotation3 = getArtPoint(pictureAfterRotation3);

        System.out.println(startArtPoint + artPointAfterRotation1 + artPointAfterRotation2 + artPointAfterRotation3);

    }

    public static Cor[][] rotate(Cor[][] map) {
        Cor[][] newMap = new Cor[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = new Cor(i, j, map[i][j].number, 0);
            }
        }
        int middle = n / 2;
        // 십자 모양 반시계 90도 회전
        // 세로 -> 가로
        for (int i = 0; i < n; i++) {
            newMap[middle][i].number = map[i][middle].number;
        }
        // 가로 -> 세로
        for (int i = 0; i < n; i++) {
            newMap[n - 1 - i][middle].number = map[middle][i].number;
        }

        // 나머지 정사각형 시계 90도 회전
        // 좌상단
        for (int i = 0; i < middle; i++) {
            for (int j = 0; j < middle; j++) {
                newMap[j][middle - 1 - i].number = map[i][j].number;
            }
        }
        // 우상단
        for (int i = 0; i < middle; i++) {
            for (int j = middle + 1; j < n; j++) {
                newMap[j - middle - 1][2 * middle - i].number = map[i][j].number;
            }
        }
        // 좌하단
        for (int i = middle + 1; i < n; i++) {
            for (int j = 0; j < middle; j++) {
                newMap[j + middle + 1][2 * middle - i].number = map[i][j].number;
            }
        }
        // 우하단
        for (int i = middle + 1; i < n; i++) {
            for (int j = middle + 1; j < n; j++) {
                newMap[j][3 * middle + 1 - i].number = map[i][j].number;
            }
        }
        return newMap;
    }

    public static int getArtPoint(Cor[][] map) {
        isVisited = new boolean[n][n];
        int groupIndex = 1; // 그룹 인덱스
        Map<Integer, int[]> groupInfo = new HashMap<>(); // 그룹 인덱스 : [칸 수, 숫자 값]
        // 그룹 만들기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j]) {
                    int[] temp = new int[2];
                    int countElement = bfs(map, map[i][j], groupIndex);
                    temp[0] = countElement;
                    temp[1] = map[i][j].number;
                    groupInfo.put(groupIndex, temp);
                    groupIndex++;
                }
            }
        }

        isVisited = new boolean[n][n];
        Map<Set<Integer>, Integer> overlapCountByGroup = new HashMap<>();
        // 그룹끼리 맞닿은 변 개수 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j]) {
                    dfs(map, i, j, overlapCountByGroup);
                }
            }
        }

        int result = 0;
        for (Set<Integer> set : overlapCountByGroup.keySet()) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int x : set) {
                temp.add(x);
            }

            result += (groupInfo.get(temp.get(0))[0] + groupInfo.get(temp.get(1))[0])
                    * groupInfo.get(temp.get(0))[1] * groupInfo.get(temp.get(1))[1] * overlapCountByGroup.get(set);
        }

        return result;
    }

    public static void dfs(Cor[][] map, int x, int y, Map<Set<Integer>, Integer> m) {
        // 1. 체크인
        isVisited[x][y] = true;
        // 2. 목적지인가?
        // 3. 연결된 곳 순회
        for (int d = 0; d < 4; d++) {
            int tempX = x + dx[d];
            int tempY = y + dy[d];
            if (tempX < 0 || tempX >= n || tempY < 0 || tempY >= n || isVisited[tempX][tempY]) continue;
            if (map[x][y].number == map[tempX][tempY].number) {
                dfs(map, tempX, tempY, m);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(map[x][y].groupNumber);
                set.add(map[tempX][tempY].groupNumber);
                m.put(set, m.getOrDefault(set, 0) + 1);
            }
        }

    }

    public static int bfs(Cor[][] map, Cor s, int index) {
        int count = 0;
        Queue<Cor> queue = new LinkedList<>();
        s.groupNumber = index;
        queue.add(s);
        isVisited[s.x][s.y] = true;

        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내기
            Cor p = queue.poll();
            // 2. 목적지인가? -> count 증가
            count++;
            // 3. 연결된 곳 순회
            for (int d = 0; d < 4; d++) {
                int tempX = p.x + dx[d];
                int tempY = p.y + dy[d];
                // 4. 갈 수 있는가?
                if (tempX < 0 || tempX >= n || tempY < 0 || tempY >= n || isVisited[tempX][tempY]) continue;
                if (p.number == map[tempX][tempY].number) {
                    // 5. 간다
                    isVisited[tempX][tempY] = true;
                    // 6. 큐에 넣기
                    map[tempX][tempY].groupNumber = index;
                    queue.add(map[tempX][tempY]);
                }
            }
        }
        return count;
    }


}
