package AlgorithmStudy.자료구조.힙.P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewelry> pqByWeight = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pqByWeight.add(new Jewelry(m, v));
        }

        // 무게 오름차순 정렬
        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);

        long sum = 0;
        PriorityQueue<Jewelry> pqByPrice = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);
        for (int bagWeight : bags) {
            while(!pqByWeight.isEmpty() && pqByWeight.peek().weight <= bagWeight) {
                pqByPrice.add(pqByWeight.poll());
            }
            if(!pqByPrice.isEmpty()) {
                sum += pqByPrice.poll().price;
            }
        }
        System.out.println(sum);
    }
}
class Jewelry {
    int weight;
    int price;

    public Jewelry(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}

