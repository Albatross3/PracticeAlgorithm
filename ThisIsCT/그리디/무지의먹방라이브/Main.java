package ThisIsCT.그리디.무지의먹방라이브;

// 프로그래머스 Lv4 문제, 알고리즘 특강 문제 중 가장 어려움
// Refactoring 필요
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static long k;
    static List<Integer> food_times = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k=Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            food_times.add(Integer.parseInt(st.nextToken()));
        }

        int time=0;
        int fakeTime=0;
        int length = food_times.size();
        while (time <= k) {
            int index=time%length;
            // 먹을 음식 없는 경우
            if(food_times.get(index)==0) {
                fakeTime++;
                time++;
                continue;
            }
            // 먹을 음식 있는 경우
            else {
                time=time-fakeTime+1;
                fakeTime=0;
                food_times.set(index, food_times.get(index)-1);
            }
        }
        // 중단 이후 먹을 음식 찾기
        boolean isFind=false;
        int findTime=0;
        while (findTime < length) {
                int index=time%length;
                if (food_times.get(index) == 0) {
                    time++;
                    findTime++;
                }
                else {
                    isFind=true;
                    break;
                }
        }

        if(!isFind) System.out.println(-1);
        else System.out.println(time%length+1);
    }
}
