package AlgorithmStudy;

// 1. 자바 정규식에 따른 split 방법
// 2. trim() 을 이용한 공백 제거
// 3.  Arrays.asList()로 만든 List에 새로운 원소를 추가하거나 삭제 할 수 없다.
// 4. 덱을 알아야 더 쉽게 풀 수 있는 문제인듯
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static String order;
    static int N;
    static String input;
    static String[] arr;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        T=Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            boolean isError=false;
            order=br.readLine();
            N=Integer.parseInt(br.readLine());
            list=new ArrayList<>(Arrays.asList(br.readLine().split("[\\[\\],]")));
            for(String s:list) System.out.println(s);
            list.remove(0);
            for(int i=0; i<order.length(); i++){
                if(order.charAt(i)=='R'){
                    Collections.reverse(list);
                }else{
                    if(list.isEmpty()) {
                        isError=true;
                        break;
                    }
                    else list.remove(0);
                }
            }
            if(isError) sb.append("error").append("\n");
            else {
                sb.append("[");
                for(int i=0; i<list.size(); i++){
                    if(i!=list.size()-1) sb.append(list.get(i)).append(",");
                    else sb.append(list.get(i));
                }
                sb.append("]").append("\n");
            }

        }
        System.out.println(sb);


    }
}
