package ThisIsCT.구현.DAY1_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// 랜덤으로 n 이하의 숫자 생성하기 (겹치지 않게)
// Java 에서는 2가지 방법 존재
// 1. Math.random (Math 클래스의 random 메서드)
// 2. Random 클래스

// 풀이 방법
// 1. random 으로 n 미만의 숫자 계속 생성하고 사용되지 않았던 index 라면 추가, 사용되었다면 다시 랜덤 추출
// 2. random 으로 n 이하의 숫자 n 개 생성 후 1-n 이랑 차례로 Map, 생성된 n개 정렬 후 mapping 된 숫자 추출
public class Main {
    static int n;
    static int[] number;
    static boolean[] isUsed;
    static List<Integer> randomNumber = new ArrayList<>();
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        number = new int[n];
        isUsed = new boolean[n];
        for (int i = 0; i < n; i++) {
            number[i]=i+1;
        }
        while(true){
            // 0포함 - n 미만의 값 랜덤 출력
            Random random = new Random();
            int randomNum = random.nextInt(n);
            if(!isUsed[randomNum]) {
                randomNumber.add(number[randomNum]);
                isUsed[randomNum]=true;
            }
            else continue;
            // 종료 조건
            if (randomNumber.size() == n) break;
        }

        // 출력
        for(int z:randomNumber){
            sb.append(z+" ");
        }
        System.out.println(sb);
    }
}
