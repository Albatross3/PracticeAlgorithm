package AlgorithmStudy.자료구조.덱;

// Deque 이라는 자료구조를 연결 리스트를 이용해서 구현
// LinkedList 의 내부 함수를 이용해도 풀이 가능

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int num;
    static String command;
    static int data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        Deque dq=new Deque();
        num = Integer.parseInt(br.readLine());
        for (int c = 0; c < num; c++) {
            st = new StringTokenizer(br.readLine());
            command=st.nextToken();
            if(st.hasMoreTokens()){
                data = Integer.parseInt(st.nextToken());
            }

            if(command.equals("push_front")) dq.push_front(data);
            else if(command.equals("push_back")) dq.push_back(data);
            else if(command.equals("pop_front")) sb.append(dq.pop_front()).append("\n");
            else if(command.equals("pop_back")) sb.append(dq.pop_back()).append("\n");
            else if(command.equals("empty")) sb.append(dq.isEmpty()).append("\n");
            else if(command.equals("front")) sb.append(dq.front()).append("\n");
            else if (command.equals("size")) sb.append(dq.size()).append("\n");
            else if (command.equals("back")) sb.append(dq.back()).append("\n");

        }
        System.out.println(sb);

    }
}
class Deque{

    class Node{
        int data;
        Node nextLink;
        Node prevLink;

        Node(int data){
            this.data=data;
            this.prevLink=null;
            this.nextLink=null;
        }
    }

    Node head;
    Node tail;


    // 비어있는지 확인
    public int isEmpty(){
        if(head==null) return 1;
        else return 0;
    }

    // 정수 x를 앞에 놓기
    public void push_front(int data){
        Node newNode=new Node(data);
        newNode.nextLink=head;

        if(isEmpty()==1) tail=newNode;
        else head.prevLink=newNode;

        newNode.prevLink=null;
        head=newNode;
    }

    // 정수 x를 뒤에 놓기
    public void push_back(int data){
        Node newNode = new Node(data);
        newNode.prevLink=tail;

        if(isEmpty()==1) head=newNode;
        else tail.nextLink=newNode;

        newNode.nextLink=null;
        tail=newNode;

    }

    // 앞의 data 삭제
    public int pop_front(){
        if(isEmpty()==1) return -1;
        else{
            Node rNode=head;
            head=rNode.nextLink;

            if(head==null) tail=null;
            else head.prevLink=null;

            return rNode.data;
        }

    }

    public int pop_back() {
        if(isEmpty()==1) return -1;
        else{
            Node rNode=tail;
            tail=rNode.prevLink;

            if (tail == null) head=null;
            else tail.nextLink = null;

            return rNode.data;
        }
    }

    public int size() {
        if (isEmpty()==1) {
            return 0;
        } else {
            int count=0;
            Node rNode=head;
            while (rNode != null) {
                count+=1;
                rNode=rNode.nextLink;
            }
            return count;
        }
    }

    // 맨 앞 출력
    public int front() {
        if (isEmpty()==1) {
            return -1;
        }else{
            return head.data;
        }
    }
    // 맨 뒤 출력
    public int back() {
        if (isEmpty()==1) {
            return -1;
        }else{
            return tail.data;
        }
    }
}
