package Baekjoon.정렬.P1713;

// 삼성 SDS 알고리즘 특강 문제
// 후보 추천하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int totalRecommended;
    static int[] recommends;
    static LinkedList<Picture> pictureBoard = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        totalRecommended = Integer.parseInt(br.readLine());
        recommends = new int[totalRecommended];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalRecommended; i++) {
            recommends[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < totalRecommended; i++) {
            int index = find(recommends[i]);
            if (index != -1) {
                pictureBoard.get(index).increaseRecommended();
            }else{
                // 빈 사진틀이 없는 경우
                if (pictureBoard.size() == N) {
                    Collections.sort(pictureBoard, new Comparator<Picture>() {
                        @Override
                        public int compare(Picture o1, Picture o2) {
                            if (o1.countRecommended > o2.countRecommended) {
                                return 1;
                            } else if (o1.countRecommended == o2.countRecommended) {
                                return o2.timestamp - o1.timestamp;
                            } else return -1;
                        }
                    });
                    pictureBoard.poll();
                }
                pictureBoard.add(new Picture(recommends[i], 1, 0));

            }
            // 1 턴 지났기 때문에 timestamp 1씩 증가
            for (Picture p : pictureBoard) {
                p.increaseTimestamp();
            }
        }

        // 학생 번호 순서로 정렬
        Collections.sort(pictureBoard, new Comparator<Picture>() {
            @Override
            public int compare(Picture o1, Picture o2) {
                return o1.studentNumber-o2.studentNumber;
            }
        });

        // 출력
        for (Picture p : pictureBoard) {
            sb.append(p.studentNumber).append(" ");
        }
        System.out.println(sb);
    }
    public static int find(int target) {
        for (int i = 0; i < pictureBoard.size(); i++) {
            if (pictureBoard.get(i).studentNumber == target) {
                return i;
            }
        }
        return -1;
    }

}
class Picture{
    int studentNumber;
    int countRecommended;
    int timestamp;

    public Picture(int studentNumber, int countRecommended, int timestamp) {
        this.studentNumber = studentNumber;
        this.countRecommended = countRecommended;
        this.timestamp = timestamp;
    }
    public void increaseRecommended() {
        countRecommended++;
    }
    public void increaseTimestamp() {
        timestamp++;
    }
}
