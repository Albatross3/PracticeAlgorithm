package AlgorithmStudy.자료구조.P10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 자료구조 - 스택
public class Main {
    static List<Integer> list=new ArrayList<>();
    static int N;
    static String order;
    static int num;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            order=st.nextToken();
            if(order.equals("push")){
                num=Integer.parseInt(st.nextToken());
                list.add(num);
            }else{
                if(order.equals("pop")){
                    if(list.size()==0) sb.append(-1).append("\n");
                    else {
                        sb.append(list.get(list.size()-1)).append("\n");
                        list.remove(list.size()-1);
                    }
                }
                if(order.equals("size")){
                    sb.append(list.size()).append("\n");
                }
                if(order.equals("empty")){
                    if(list.size()==0) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                }
                if(order.equals("top")){
                    if(list.size()==0) sb.append(-1).append("\n");
                    else sb.append(list.get(list.size()-1)).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

}
