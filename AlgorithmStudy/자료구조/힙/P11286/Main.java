package AlgorithmStudy.자료구조.힙.P11286;

// 절댓값 힙
// PriorityQueue 에서 Comparator 를 활용하기 때문에 알아둘 문제
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) > Math.abs(o2)) return 1;
                else if(Math.abs(o1) == Math.abs(o2)) return o1-o2;
                else return -1;
            }
        });
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input==0) {
                if(pq.size()==0) sb.append(0).append("\n");
                else sb.append(pq.poll()).append('\n');
            }else {
                pq.add(input);
            }
        }
        System.out.println(sb);


    }
}




