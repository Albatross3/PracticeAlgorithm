package ThisIsCT.그리디.모험가길드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] adventurer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        adventurer = new int[N];
        for (int i = 0; i < N; i++) {
            adventurer[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(adventurer);

        int countGroup = 0;
//        int i=0;
//        int cumCount=0; // 그룹 찾으면 다시 0됨
//        while (true) {
//            // 종료 조건
//           int nextPoint=adventurer[i]+(i-cumCount-1);
//           if(nextPoint>=N) break;
//
//           // 그룹 찾은 경우
//           if(adventurer[nextPoint]==adventurer[i]){
//                countGroup++;
//                cumCount=0;
//                i=nextPoint+1;
//           }
//           // 못 찾은 경우
//           else {
//               cumCount++;
//               i++;
//           }
//        }
        int count=0;
        for(int fear:adventurer){
            count++;
            if(count>=fear){
                countGroup++;
                count=0;
            }
        }

        System.out.println(countGroup);

    }
}
