package AlgorithmStudy.조합론;

// Deque 이라는 자료구조를 연결 리스트를 이용해서 구현

public class Main3 {
    public static void main(String[] args) {


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
    public boolean isEmpty(){
        if(head==null) return true;
        else return false;
    }

    // 정수 x를 앞에 놓기
    public void push_front(int data){
        Node newNode=new Node(data);
        newNode.nextLink=head;

        if(isEmpty()) tail=newNode;
        else head.prevLink=newNode;

        newNode.prevLink=null;
        head=newNode;
    }

    // 정수 x를 뒤에 놓기
    public void push_last(int data){
        Node newNode = new Node(data);
        newNode.prevLink=tail;

        if(isEmpty()) head=newNode;
        else tail.nextLink=newNode;

        newNode.nextLink=null;
        tail=newNode;

    }

    // 앞의 data 삭제
    public int pop_front(){
        if(isEmpty()) return -1;
        else{
            Node rNode=head;
            head=rNode.nextLink;

            if(head==null) tail=null;
            else head.prevLink=null;

            return rNode.data;
        }

    }

    public int pop_last() {
        if(isEmpty()) return -1;
        else{
            Node rNode=tail;
            tail=rNode.prevLink;

            if (tail == null) head=null;
            else tail.nextLink = null;

            return rNode.data;
        }
    }

    public int size() {
        if (isEmpty()) {
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
}
