package DAY05;

import java.sql.SQLOutput;

public class Practice {
    public static void main(String[] args) {
        int[] arr=new int[5];  // 자동으로 0으로 초기화 됨
        boolean[] arr2=new boolean[5]; // 자동으로 false로 초기화 됨
        Student[] arr3=new Student[5]; // reference type이라 null로 초기화 됨

        for (int i = 0; i < arr.length; i++) {
            System.out.print( arr[i] +" " +arr2[i]+" " +arr3[i]);
            System.out.println();

        }
        System.out.println('C'-'A');  //2 , 문자 간격만큼 char끼리의 뺄셈이 숫자가 됨
        System.out.println('1'-'0');  //1
        System.out.println((int)'0'); // 아스키 코드 값으로 48
        System.out.println((int)'A'); // 아스키 코드 값으로 65
        System.out.println((int)'a'); // 아스키 코드 값으로 97

//        //  밑과 같은 접근이 안되는 듯
//        arr3[0].name="이동준";
//        arr3[0].age=27;
//        System.out.println(arr3[0].age);  // 주소 나올듯
    }
}
class Student{
    String name;
    int age;

    public Student(String name, int age){
        this.name=name;
        this.age=age;
    }
}