package Programmers.KAKAO_INTERN_2021.L3;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
    }
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();

        Node current = new Node(0,null,null);
        Node root = current;
        for(int i=1; i<n; i++) {
            Node newNode = new Node(i,null,null);
            current.down = newNode;
            newNode.up = current;
            current = newNode; // 마지막이 current가 된다
        }

        current = root;
        for(int i=0; i<k; i++) {
            current = current.down;
        }

        Stack<Node> stack = new Stack<>(); // 복구를 위한 stack

        for(int i=0; i<cmd.length; i++) {
            String[] split = cmd[i].split(" ");
            String order = split[0];
            // 위로 이동
            if(order.equals("U")) {
                int X = Integer.parseInt(split[1]);
                for(int x=0; x<X; x++) {
                    current = current.up;
                }
            }
            // 아래로 이동
            else if(order.equals("D")) {
                int X = Integer.parseInt(split[1]);
                for(int x=0; x<X; x++) {
                    current = current.down;
                }
            }
            // 삭제
            else if(order.equals("C")) {
                current.up.down = current.down;
                current.down.up = current.up;
                stack.push(current);
                if(current.down==null) current = current.up;
                else current = current.down;
            }
            // 복구
            else if(order.equals("Z")) {
                Node restored = stack.pop();
                restored.up.down = restored;
                restored.down.up = restored;
            }
        }

        // current 위치를 맨 위로 옮김
        while(current.up!=null) {
            current = current.up;
        }

        // 모든 value 값들을 set 에 넣기
        Set<Integer> set = new HashSet<>();
        while(current.down!=null) {
            set.add(current.value);
            current = current.down;
        }

        for(int i=0; i<n; i++) {
            if(set.contains(i)) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }
}
class Node {
    int value; // 0~n-1 까지의 숫자
    Node up;
    Node down;

    public Node (int value, Node up, Node down) {
        this.value = value;
        this.up = up;
        this.down = down;
    }
}
