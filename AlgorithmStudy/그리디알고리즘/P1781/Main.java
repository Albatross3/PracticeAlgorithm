package AlgorithmStudy.그리디알고리즘.P1781;

// Greedy + 우선순위 큐
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Homework> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int numRamen = Integer.parseInt(st.nextToken());
            pq.add(new Homework(deadline, numRamen));
        }
        // Greedy Algorithm
        PriorityQueue<Integer> result = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            Homework h = pq.poll();
            result.add(h.numRamen);
            if (h.deadline < result.size()) {
                result.poll();
            }
        }
        // result 우선순위 큐의 값 더하기
        int answer = 0;
        for (int ramen : result) {
            answer += ramen;
        }
        System.out.println(answer);
    }
}
class Homework implements Comparable<Homework>{
    int deadline;
    int numRamen;

    public Homework(int deadline, int numRamen) {
        this.deadline = deadline;
        this.numRamen = numRamen;
    }

    @Override
    public int compareTo(Homework o) {
        return this.deadline-o.deadline;
    }
}
