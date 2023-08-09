package Baekjoon.백트래킹.P14888;

// 같은 것이 있는 순열 백트래킹 문제
// 삼성 SW 역량문제
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] num;
    static char[] operator={'+','-','*','/'};
    static char[] result;
    static int[] isCount;
    static List<String> cases=new ArrayList<>();
    static int N;
    static int count=0;

    static int MAX=-1000000000,MIN=1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 입력 부분
        N=Integer.parseInt(br.readLine());
        num=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            num[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        isCount=new int[4];
        for(int i=0; i<4; i++){
            isCount[i]=Integer.parseInt(st.nextToken());
        }
        result=new char[N-1];

        // 모든 연산자 조합 저장
        backTracking(0);

        // 모든 조합에 대해 계산
        for(int c=0; c<count; c++){
            String tempComb=cases.get(c);

            int temp=calculate(tempComb.charAt(0),num[0],num[1]);
            for(int i=1; i<N-1; i++){
                temp=calculate(tempComb.charAt(i),temp,num[i+1]);
            }
            MAX=Math.max(MAX,temp);
            MIN=Math.min(MIN,temp);
        }
        System.out.println(MAX);
        System.out.println(MIN);

    }
    public static void backTracking(int level){
        if(level==N-1){
            StringBuilder sb=new StringBuilder();
            for(char c: result) sb.append(c);
            cases.add(sb.toString());
            count++;
            return;
        }
        for(int i=0; i<4; i++){
            if(isCount[i]!=0){
                result[level]=operator[i];
                isCount[i]--;
                backTracking(level+1);
                isCount[i]++;
            }
        }
    }
    public static int calculate(char c, int op1,int op2){
        switch (c){
            case '+':
                return op1+op2;
            case '-':
                return op1-op2;
            case '*':
                return op1*op2;
            case '/':
                if(op1<0) {
                    int temp=(-op1)/op2;
                    return -temp;
                }
                else return op1/op2;
        }
        return 0;
    }
}

