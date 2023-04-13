package AlgorithmStudy.서로소집합.P4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static int F;
    static Map<String, Integer> map;
    static int[] parent;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            parent = new int[2 * F + 1];
            count = new int[2 * F + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                count[i] = 1;
            }
            int index = 1;
            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                if (!map.containsKey(name1)) {
                    map.put(name1, index++);
                }
                if (!map.containsKey(name2)) {
                    map.put(name2, index++);
                }

                // 둘 다 map 에 있으므로 union 연산
                sb.append(union(map.get(name1), map.get(name2))).append("\n");
            }
        }
        System.out.println(sb);


    }

    public static int find(int x) {
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    // root 를 어디 쪽에 연결시킬 것인지? -> 무조건 작은 쪽에 연결
    public static int union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rootX < rootY) {
                parent[rootY] = rootX;
                count[rootX] += count[rootY];
                return count[rootX];
            } else {
                parent[rootX] = rootY;
                count[rootY] += count[rootX];
                return count[rootY];
            }
        }
        return count[rootX];
    }

}