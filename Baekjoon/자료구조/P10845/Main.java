package Baekjoon.자료구조.P10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 자료구조 - 큐
// 구현방식 - 1. 배열로 구현하기
// 구현방식 - 2. Linked list로 구현하기
public class Main {
    static int T;
    static String order;
    static int value;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        T=Integer.parseInt(br.readLine());
        // 클래스에서 큐 객체 생성
        Queue q=new Queue();

        for(int i=0; i<T; i++){
            st=new StringTokenizer(br.readLine());
            order=st.nextToken();
            if(order.equals("push")){
                value=Integer.parseInt(st.nextToken());
                q.push(value);
            }
            if(order.equals("pop")){
                sb.append(q.pop()).append("\n");
            }
            if(order.equals("size")){
                sb.append(q.size()).append("\n");
            }
            if(order.equals("empty")){
                sb.append(q.isEmpty()).append("\n");
            }
            if(order.equals("front")){
                sb.append(q.front()).append("\n");
            }
            if(order.equals("back")){
                sb.append(q.back()).append("\n");
            }
        }
        System.out.print(sb);
    }

}
class Queue{
    int front;
    int rear;
    int capacity=10000;
    int[] queue;

    public Queue() {
        int front=0;
        int rear=0;
        queue=new int[capacity];
    }

    public void push(int value){
        queue[rear++]=value;
    }
    public int pop(){
        if(front==rear) return -1;
        else return queue[front++];

    }
    public int size(){
        return rear-front;
    }
    public int isEmpty(){
        if(front==rear) return 1;
        else return 0;
    }
    public int front(){
        if (isEmpty()==1){
            return -1;
        }else{
            return queue[front];
        }
    }
    public int back(){
        if(isEmpty()==1){
            return -1;
        }else{
            return queue[rear-1];
        }
    }
}
