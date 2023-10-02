package SAMSUNG.하반기_2016.문제2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] initialMap;

    static int[] direction = {1, 2, 3, 4}; // 북(1) 동(2) 남(3) 서(4)
    static int[] combination = new int[5];
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        initialMap = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                initialMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        doGameByCombination(0);
        System.out.println(max);
    }
    public static void doGameByCombination(int depth) {
        if (depth == 5) {
            max = Math.max(max, game());
            return;
        }
        for (int i = 0; i < direction.length; i++) {
            combination[depth] = direction[i];
            doGameByCombination(depth + 1);
        }
    }

    private static int game() {
        // 1. map 초기화
        int[][] map = initializeMap();
        // 2. 이동
        move(map);
        // 3. max 구하기
        return getMax(map);
    }

    private static int[][] initializeMap() {
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = initialMap[i][j];
            }
        }
        return map;
    }

    private static void move(int[][] map) {
        for (int i = 0; i < combination.length; i++) {
            // 블록 만들기
            Block[][] temp = makeArray(map, combination[i]);
            // 블록 합치기
            List<Stack<Block>> list = unionBlock(temp);
            // map 에 반영하기
            writeToMap(map, list, combination[i]);
        }
    }

    private static Block[][] makeArray(int[][] map, int order) {
        Block[][] blocks = new Block[N][N];
        if (order == 1) {
            for (int c = 0; c < N; c++) {
                for (int r = 0; r < N; r++) {
                    blocks[c][r] = new Block(map[r][c], false);
                }
            }
        } else if (order == 2) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    blocks[r][c] = new Block(map[r][N - c - 1], false);
                }
            }
        } else if (order == 3) {
            for (int c = 0; c < N; c++) {
                for (int r = 0; r < N; r++) {
                    blocks[c][r] = new Block(map[N - r - 1][c], false);
                }
            }
        } else if (order == 4) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    blocks[r][c] = new Block(map[r][c], false);
                }
            }
        }
        return blocks;
    }

    private static List<Stack<Block>> unionBlock(Block[][] blocks) {
        List<Stack<Block>> list = new ArrayList<>();
        for (int i = 0; i < blocks.length; i++) {
            Stack<Block> stack = new Stack<>();
            // 각 줄에 대해서 union 작업
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j].number == 0) continue;
                if (stack.isEmpty()) stack.push(blocks[i][j]);
                else {
                    if (!stack.peek().isUnion && stack.peek().number == blocks[i][j].number) {
                        stack.pop();
                        stack.push(new Block(blocks[i][j].number * 2, true));
                    } else{
                        stack.push(blocks[i][j]);
                    }
                }
            }
            // 남은 부분 0으로 채우기
            while (stack.size() != N) {
                stack.push(new Block(0, false));
            }
            list.add(stack);
        }
        return list;
    }

    private static void writeToMap(int[][] map, List<Stack<Block>> list, int order) {
        if (order == 1) {
            int c = 0;
            for (Stack<Block> s : list) {
                int size = s.size();
                int r = 0;
                while (!s.isEmpty()) {
                    map[size - 1 - r][c] = s.pop().number;
                    r++;
                }
                c++;
            }
        } else if (order == 2) {
            int r = 0;
            for (Stack<Block> s : list) {
                int size = s.size();
                int c = 0;
                while (!s.isEmpty()) {
                    map[r][N - size + c] = s.pop().number;
                    c++;
                }
                r++;
            }
        } else if (order == 3) {
            int c = 0;
            for (Stack<Block> s : list) {
                int size = s.size();
                int r = 0;
                while (!s.isEmpty()) {
                    map[N - size + r][c] = s.pop().number;
                    r++;
                }
                c++;
            }
        } else if (order == 4) {
            int r = 0;
            for (Stack<Block> s : list) {
                int size = s.size();
                int c = 0;
                while (!s.isEmpty()) {
                    map[r][size - 1 - c] = s.pop().number;
                    c++;
                }
                r++;
            }
        }
    }

    private static int getMax(int[][] map) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    static class Block {
        int number;
        boolean isUnion;

        public Block(int number, boolean isUnion) {
            this.number = number;
            this.isUnion = isUnion;
        }
    }
}
