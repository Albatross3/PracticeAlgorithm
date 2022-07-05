package AlgorithmStudy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int n;
    static Stack<Integer> stack = new Stack<>();
    static boolean isPossible=true;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 다음에 넣어야할 숫자
        int index=1;
        for (int i = 0; i < n; i++) {
            int target = Integer.parseInt(br.readLine());

            if(index <= target){
                while (index <= target) {
                    stack.push(index);
                    sb.append("+").append("\n");
                    index++;
                }
            }else if(stack.peek()!=target){
                System.out.println("NO");
                return;
            }
            stack.pop();
            sb.append("-").append("\n");
        }
        System.out.println(sb);
    }
}
