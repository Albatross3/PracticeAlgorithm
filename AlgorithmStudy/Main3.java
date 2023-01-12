package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {
    static int N;
    static List<Conference> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Conference(s, e));
        }

        Collections.sort(list);

        // 끝나는 시간 기준
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Conference c : list) {
            if(pq.peek() == null) {
                pq.add(c.end);
                continue;
            }
            if(pq.peek() <= c.start) {
                pq.poll();
                pq.add(c.end);
            }
            else {
                pq.add(c.end);
            }
        }
        System.out.println(pq.size());
    }



}
class Conference implements Comparable<Conference>{
    int start;
    int end;

    public Conference(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Conference o) {
        if(this.start == o.start) return this.end - o.end;
        else return this.start - o.start;
    }
}


