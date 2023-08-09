package Baekjoon.자료구조.P1991;

// 자료구조 - 트리와 트리 순회
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        Tree tree=new Tree();
        for(int i=0; i<N; i++){
            input=br.readLine().split(" ");
            tree.createNode(input[0].charAt(0),input[1].charAt(0),input[2].charAt(0));
        }
        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }

}
class Node{
    char data;
    Node leftChild,rightChild;
    public Node(char data){
        this.data=data;
        leftChild=null;
        rightChild=null;
    }
}
class Tree{
    Node root;
    public void createNode(char data,char leftData, char rightData){
        if(root==null){
            root=new Node(data);
            if(leftData!='.') root.leftChild=new Node(leftData);
            if(rightData!='.') root.rightChild=new Node(rightData);
        }else{
            searchNode(root,data,leftData,rightData);
        }
    }
    public void searchNode(Node root,char data, char leftData, char rightData){
        if(root==null) return;
        else if(root.data==data){
            if(leftData!='.') root.leftChild=new Node(leftData);
            if(rightData!='.') root.rightChild=new Node(rightData);
        }
        else{
            searchNode(root.leftChild,data,leftData,rightData);
            searchNode(root.rightChild,data,leftData,rightData);
        }
    }

    public void preOrder(Node node){
        System.out.print(node.data);
        if(node.leftChild!=null) preOrder(node.leftChild);
        if(node.rightChild!=null) preOrder(node.rightChild);
    }

    public void inOrder(Node node){
        if(node.leftChild!=null) inOrder(node.leftChild);
        System.out.print(node.data);
        if(node.rightChild!=null) inOrder(node.rightChild);
    }
    public void postOrder(Node node){
        if(node.leftChild!=null) postOrder(node.leftChild);
        if(node.rightChild!=null) postOrder(node.rightChild);
        System.out.print(node.data);
    }
}
