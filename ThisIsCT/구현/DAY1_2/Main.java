package ThisIsCT.구현.DAY1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 가장 많이 증가하는/감소하는 구간의 길이 구하기
// 마지막 부분에서 빠뜨리기 쉬워서 생각보다 오래 걸리는 문제
public class Main {
    static List<Integer> arr = new ArrayList<>();
    static List<Integer> increase = new ArrayList<>();
    static List<Integer> decrease = new ArrayList<>();
    static int[] answer = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(true){
            int num = Integer.parseInt(st.nextToken());
            // 종료 조건
            if(num==-1) break;
            arr.add(num);
        }
        System.out.println(arr);

        int index = 0;
        int incCount=1;
        int decCount=1;
        while (true) {
            // 증가 구간 확인
            if(arr.get(index)<arr.get(index+1)) incCount++;
            else {
                increase.add(incCount);
                incCount=1;
            }

            // 감소 구간 확인
            if(arr.get(index)>arr.get(index+1)) decCount++;
            else {
                decrease.add(decCount);
                decCount=1;
            }
            index++;
            // 종료 조건
            if(index==arr.size()-1){
                increase.add(incCount);
                decrease.add(decCount);
                break;
            }
        }

        // 증가/감소 구간 정렬
        Collections.sort(increase);
        Collections.sort(decrease);

        // 출력
        if (increase.get(increase.size() - 1) <= 2) {
            answer[0]=1;
        }else{
            answer[0] = increase.get(increase.size() - 1);
        }

        if (decrease.get(decrease.size() - 1) <= 2) {
            answer[1]=1;
        }else{
            answer[1]=decrease.get(decrease.size() - 1);
        }

        System.out.println(increase);
        System.out.println(decrease);
        for (int i = 0; i < 2; i++) {
            System.out.print(answer[i]+" ");
        }
    }
}
