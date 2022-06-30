package AlgorithmStudy.기타.P5430;

// 백준 5430 - AC
// 1. 자바 정규식에 따른 split 방법
// 2. trim() 을 이용한 공백 제거
// 3.  Arrays.asList()로 배열을 Collection 형태로 변환
// 4. 덱을 알아야 더 쉽게 풀 수 있는 문제인듯 - 덱은 앞/뒤 모두 삽입/삭제가 O(1)이다
// 5. dq 의 size 를 줄여가면서 출력할 때 for 문을 쓰면 안되고 while(dq.isEmpty())로 접근하자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int T;
    static String order;
    static int N;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        T=Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            order=br.readLine();
            N = Integer.parseInt(br.readLine());
            input=br.readLine();
            Deque<String> dq;
            if(N==0) {
                dq=new LinkedList<>();
            }
            else{
                input = input.substring(1, input.length() - 1);
                String[] data = input.split(",");
                dq=new LinkedList<>(Arrays.asList(data));
            }


            boolean isReverse = false;
            boolean isError=false;
            for (int i = 0; i < order.length(); i++) {
                if (order.charAt(i) == 'R') {
                    isReverse= !isReverse; // 간단한 표현
                } else {
                    if(isReverse) {
                        if(dq.isEmpty()) {
                            isError=true;
                            break;
                        }
                        else dq.removeLast();
                    } else {
                        if(dq.isEmpty()) {
                            isError=true;
                            break;
                        }
                        else dq.removeFirst();
                    }
                }
            }

            // 출력
            // dq size 가 계속해서 줄어들기 때문에 for 문 안에 제거를 하면 조심해야 함
            int size=dq.size();
            if(isError) sb.append("error").append("\n");
            else{
                if(size==0) sb.append("[]").append("\n");
                else{
                    sb.append("[");
                    if(isReverse){
                        sb.append(dq.removeLast());
                        while(!dq.isEmpty())
                            sb.append(",").append(dq.removeLast());
                    }else{
                        sb.append(dq.removeFirst());
                        while(!dq.isEmpty())
                            sb.append(",").append(dq.removeFirst());
                    }
                    sb.append("]").append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
