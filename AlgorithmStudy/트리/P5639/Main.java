package AlgorithmStudy.트리.P5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()), null, null);
        String s;
        // 이진 탐색 트리 생성
        while (true) {
            s = br.readLine();
            if(s==null || s.equals("")) break;
            int v = Integer.parseInt(s);
            root.insert(v);
        }

        // 후위 탐색
        postOrder(root);

        System.out.println(sb);

    }
    public static void postOrder(Node current) {
        if (current == null) return;
        postOrder(current.left);
        postOrder(current.right);
        sb.append(current.value).append("\n");
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void insert(int v) {
        if (v < this.value) {
            if (this.left == null) {
                this.left = new Node(v, null, null);
            } else {
                this.left.insert(v);
            }
        } else if (v > this.value) {
            if (this.right == null) {
                this.right = new Node(v, null, null);
            } else {
                this.right.insert(v);
            }
        }
    }


}