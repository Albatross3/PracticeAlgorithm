package AlgorithmStudy.그리디알고리즘.P11000;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<Conference> lectures = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lectures.add(new Conference(s, e));
        }

        Collections.sort(lectures);

        // lecture 의 end 값이 들어있음 -> 각 원소들이 강의실
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures.get(0).end);
        for (int i = 1; i < lectures.size(); i++) {
            Conference c = lectures.get(i);
            if (pq.peek() <= c.start) {
                pq.poll();
            }
            pq.add(c.end);
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
        return this.start - o.start;
    }
}


