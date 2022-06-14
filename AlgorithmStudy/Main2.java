package AlgorithmStudy;

// 1. HashMap 2개 만들고 try-catch 문으로 입력 처리
// 2. HashMap 2개 만들고 문제 조건 활용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main2 {
    static int N,M;
    static HashMap<String,Integer> map1=new HashMap<>();
    static HashMap<Integer,String> map2=new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            String pocketMon=br.readLine();
            map1.put(pocketMon,i+1);
            map2.put(i+1,pocketMon);
        }


//        for (int i = 0; i < M; i++) {
//            String numberOrName=br.readLine();
//            try{
//                int number=Integer.parseInt(numberOrName);
//                sb.append(map2.get(number)).append("\n");
//            }catch (NumberFormatException e){
//                String name=numberOrName;
//                sb.append(map1.get(name)).append("\n");
//            }
//        }
        // 문제에 들어있는 조건을 활용
        for (int i = 0; i < M; i++) {
            String numberOrName=br.readLine();
            if(numberOrName.charAt(0)>=65 && numberOrName.charAt(0)<=90) sb.append(map1.get(numberOrName)).append("\n");
            else sb.append(map2.get(Integer.parseInt(numberOrName))).append("\n");
        }
        System.out.println(sb);
    }
}
