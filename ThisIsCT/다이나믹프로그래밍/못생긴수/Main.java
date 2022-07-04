package ThisIsCT.다이나믹프로그래밍.못생긴수;
// 알고리즘 유형별 기출문제
// 다이나믹 프로그래밍 문제 - 못생긴 수
// 2,3,5 각각에 대해서 곱해졌던 위치를 기억해 두는 것이 핵심
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static int[] set = new int[3];
    static List<Integer> uglyNumber = new ArrayList<>();
    static int i2,i3,i5=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        uglyNumber.add(1);
        set[0]=2; set[1]=3; set[2]=5;

        for(int i=1; i<N; i++){
            int min = Math.min(set[0], Math.min(set[1], set[2]));
            uglyNumber.add(min);
            if (min == set[0]) {
                i2 += 1;
                set[0] = uglyNumber.get(i2) * 2;
            }
            if (min == set[1]) {
                i3+=1;
                set[1]=uglyNumber.get(i3)*3;
            }
            if (min == set[2]) {
                i5+=1;
                set[2] = uglyNumber.get(i5) * 5;
            }
        }
        System.out.println(uglyNumber.get(N-1));
    }
}
