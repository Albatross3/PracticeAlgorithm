package AlgorithmStudy.정렬.P1713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 핵심
// 주어진 list 에서 해당 target 값이 있는지 찾는 방법은 여러가지가 있는데
// 1. 탐색(완전탐색 or 이진탐색)
// 2. 해당 list를 모두 memory에 저장하고 target에 해당하는 index로 확인 <공간을 활용하는 방법>

// 설계
// 추천된 후보가 사진틀에 있는 경우
// 추천된 후보가 사진틀에 없는 경우
//      -사진틀이 비어있는 경우
//      -사진틀이 비어있지 않은 경우

// PrioirtyQueue 는 삽입/삭제 될 때마다 정렬이 되므로 중간에 추천수가 증가하는 순간에는
// 정렬이 되지 않아서 PriortyQueue를 사용할 수 없다
public class Main2 {
    static int N;
    static int T;
    static Student[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        // 사진틀
        ArrayList<Student> pictureBoard = new ArrayList<>();
        // 공간적으로 저장하기 위함
        board = new Student[101]; // 가능한 학생 번호 1~100

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            int number = Integer.parseInt(st.nextToken());
            // 사진틀에 있는 경우
            if (board[number]!=null && board[number].isIn) {
                board[number].numRecommend++;
            }
            // 사진틀에 없는 경우
            else if(board[number]==null || !board[number].isIn) {
                Student s = new Student(number, 1, i, true);
                // 사진틀이 full 인 경우 -> 삭제 후 추가
                if (pictureBoard.size() == N) {
                    // 삭제
                    Collections.sort(pictureBoard);
                    Student temp = pictureBoard.remove(0);
                    temp.isIn = false;
                    temp.numRecommend = 0;
                }
                // full 이나 비어있는 경우나 추가는 똑같음
                // 추가 -> Student[] 과 PriorityQueue<Student> 에 모두 추가
                board[number] = s;
                pictureBoard.add(s);
            }
        }
        Collections.sort(pictureBoard, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.studentNumber-o2.studentNumber;
            }
        });
        for (Student s : pictureBoard) {
            System.out.print(s.studentNumber+" ");
        }
    }
}

class Student implements Comparable<Student> {
    int studentNumber;
    int numRecommend;
    int timeStamp;
    boolean isIn;

    public Student(int studentNumber, int numRecommend, int timeStamp, boolean isIn) {
        this.studentNumber = studentNumber;
        this.numRecommend = numRecommend;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override
    public int compareTo(Student o) {
        if (this.numRecommend < o.numRecommend) return -1;
        else if(this.numRecommend== o.numRecommend) return this.timeStamp - o.timeStamp;
        else return 1;
    }
}
