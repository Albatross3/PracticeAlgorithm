package Baekjoon.자료구조.P2164;

// 자료구조 - LinkedList 활용
// 자료구조 - Queue 로도 풀이 가능
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        LinkedList<Integer> linkedList=new LinkedList<>();

        // 역순으로 값 입력
        for(int i=1; i<=N; i++){
            Integer INT=i;
            linkedList.addFirst(INT);
        }

        // 2. 그 다음 카드 밑으로 옮기기

        while(linkedList.size()!=1){
            // 1. 맨 위 날리기
            linkedList.removeLast();
            // 2. 그 다음 카드 밑으로 옮기기
            Integer move=linkedList.get(linkedList.size()-1);
            linkedList.removeLast();
            linkedList.addFirst(move);
        }
        System.out.println(linkedList.get(0));
    }
}
