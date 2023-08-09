package Baekjoon.시뮬레이션.P1713;

// 시뮬레이션 - 후보 추천하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int totalNum;
    static List<Student> pictures;
    static boolean[] isIn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        pictures = new ArrayList<>(N);
        isIn = new boolean[101];

        totalNum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalNum; i++) {
            int studentNumber = Integer.parseInt(st.nextToken());
            // 사진판에 학생이 게시되어 있는 경우
            if (isIn[studentNumber]) {
                for (Student s : pictures) {
                    if (s.getStudentNumber() == studentNumber) s.increaseCount();
                }
            }
            // 사진판에 학생이 없는 경우
            else {
                // 사진틀이 비어있는 경우
                if (pictures.size() != N) {
                    pictures.add(new Student(studentNumber, 1, i));
                    isIn[studentNumber] = true;
                }
                // 사진틀이 전부 찬 경우
                else {
                    Collections.sort(pictures);
                    isIn[pictures.get(N-1).getStudentNumber()]=false;
                    pictures.remove(N-1);
                    pictures.add(new Student(studentNumber,1,i));
                    isIn[studentNumber]=true;
                }
            }
        }
        Collections.sort(pictures, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.studentNumber > o2.studentNumber) return 1;
                else return -1;
            }
        });
        for(Student s: pictures){
            sb.append(s.getStudentNumber()).append(" ");
        }
        System.out.println(sb);
    }

}

class Student implements Comparable<Student> {
    int studentNumber;
    int count;
    int time;

    public Student(int studentNumber, int count, int time) {
        this.studentNumber = studentNumber;
        this.count = count;
        this.time = time;
    }

    public void increaseCount() {
        count += 1;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    @Override
    public int compareTo(Student o) {
        if(this.count < o.count) return 1;
        else if(this.count==o.count) {
            if(this.time < o.time) return 1;
            else return -1;
        }
        else return -1;
    }
}

