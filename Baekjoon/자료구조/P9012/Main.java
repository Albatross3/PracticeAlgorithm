package Baekjoon.자료구조.P9012;

// 자료구조 - 스택 활용문제
import java.io.*;

public class Main {
    static int T;
    static char[] PS;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        T=Integer.parseInt(br.readLine());
        for(int x=0; x<T; x++){
            PS=br.readLine().toCharArray();
            boolean isVPS=true;
            Stack s=new Stack();
            for(int i=0; i<PS.length; i++){
                if(PS[i]=='('){
                    s.push('(');
                }else {
                    int result=s.pop();
                    if (result == -1) {
                        isVPS=false;
                        break;
                    }
                }
            }
            // 1. 크기가 0이 아닌 경우 -> NO
            if(s.size()!=0){
                sb.append("NO").append("\n");
            }else{
                if(isVPS==true){
                    sb.append("YES").append("\n");
                }
                // 2. 크기는 0이지만 중간에 크기 0에서 pop 한 경우 -> NO
                else{
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);


    }
}
class Stack{
    int top;
    int capacity;
    char[] stack;

    public Stack(){
        top=-1;
        capacity=50;
        stack=new char[capacity];
    }
    public void push(char c){
        stack[++top]=c;
    }
    public int pop(){
        if(top==-1) return -1;
        else {
            top--;
            return 0;
        }
    }
    public int size(){
        if(top==-1) return 0;
        else return top+1;
    }
}