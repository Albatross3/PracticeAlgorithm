package AlgorithmStudy.P11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 하노이의 탑 문제 (아직 못 품)
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        //stack 사용시 int가 아닌 Integer형 선언 필요
        Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        Stack<Integer> s3=new Stack<>();

        for(int i=N; i>0; i--){
            s1.push(i);
        }

        while(s3.size()==N){
            int move=s1.pop();


        }
    }
}
