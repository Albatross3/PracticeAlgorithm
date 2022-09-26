package Homework.Algorithm.순차탐색;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clock {
    public static void main(String[] args) {
        // 파일에서 읽어들어 저장해둘 list 생성
        List<Integer> list = new ArrayList<>();

        try {
            File file = new File("./Homework/Algorithm/순차탐색/1.in"); // 주어진 파일 읽어옴 -> file 객체
            Scanner sc = new Scanner(file); // file 객체를 Scanner 를 통해 읽어들일 준비
            // 다음 줄에 읽어들일 게 있다면 계속 진행
            // int 형으로 data 를 읽고 list 에 저장
            while (sc.hasNextLine()) {
                int temp = sc.nextInt();
                list.add(temp);
            }

        } catch (Exception e) { // 예외처리
            e.printStackTrace();
        }
        // list 길이 확인 -> 제대로 읽어왔는지 확인하기 위함
        System.out.println(list.size());
        // 찾으려는 값을 입력받음
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();

        // 시작 시각을 millisecond 로 나타냄
        long start = System.currentTimeMillis();

        // 순차 탐색 알고리즘
        // index 를 0부터 1씩 증가해가면서 list 의 숫자와 목표하는 숫자를 비교
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)==target) {
                System.out.println(list.get(i).toString());
                break;
            }
        }
        // 종료 시각
        long stop = System.currentTimeMillis();
        // 종료 시각 - 시작 시각
        long result = (stop - start);
        // 순차탐색 진행된 시간 출력
        System.out.println("걸린 시간은 " + result + "입니다");
    }
}
