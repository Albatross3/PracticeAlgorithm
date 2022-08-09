package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        LinkedList<Integer> queue = new LinkedList<>();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            String order=st.nextToken();
            if(order.equals("push")) {
                int input = Integer.parseInt(st.nextToken());
                queue.offer(input);
            } else if (order.equals("pop")) {
                if(queue.isEmpty()) sb.append(-1).append("\n");
                else sb.append(queue.poll()).append("\n");
            } else if (order.equals("size")) {
                sb.append(queue.size()).append("\n");
            } else if (order.equals("empty")) {
                if(queue.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if (order.equals("front")) {
                if(queue.isEmpty()) sb.append(-1).append("\n");
                else sb.append(queue.peek()).append("\n");
            } else if (order.equals("back")) {
                if(queue.isEmpty()) sb.append(-1).append("\n");
                else sb.append(queue.peekLast()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
