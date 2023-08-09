package Baekjoon.정렬.P11650;

// 백준 11650번 좌표 정렬하기 (11651번과 똑같음)
// 1. 클래스 (멤버 변수, 생성자, getter 함수)
// 2. Comparable 인터페이스 ->  compareTo 함수 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static List<Cor> arr=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            int tempX=Integer.parseInt(st.nextToken());
            int tempY=Integer.parseInt(st.nextToken());
            arr.add(new Cor(tempX,tempY));
        }
        Collections.sort(arr);

        for(int i=0; i<N; i++){
            sb.append(arr.get(i).getX()).append(" ").append(arr.get(i).getY()).append("\n");
        }
        System.out.println(sb);
    }
}
class Cor implements Comparable<Cor>{
    int x,y;
    public Cor(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // 1이면 자리 바꾸기, -1 or 0이면 그대로
    @Override
    public int compareTo(Cor o) {
        if(this.x==o.x){
            if(this.y < o.y) return -1;
            else return 1;
        }
        else if(this.x < o.x) return -1;
        else return 1;
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }
}
