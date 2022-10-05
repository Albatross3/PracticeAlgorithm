package AlgorithmStudy;

// map 에서 대응될 때 ArrayList : Integer
// ArrayList 의 원소들만 가지고 찾기 가능

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main4 {
    static int E, S, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> target = new ArrayList<>();
        target.add(E);
        target.add(S);
        target.add(M);

        Map<ArrayList<Integer>, Integer> map = new LinkedHashMap<>();
        int e = 0, s = 0, m = 0;
        for (int i = 0; i < 7980; i++) {
            e = i % 15 + 1;
            s = i % 28 + 1;
            m = i % 19 + 1;

            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(e);
            temp.add(s);
            temp.add(m);

            map.put(temp, i+1);
        }
        System.out.println(map.get(target));
    }
}
