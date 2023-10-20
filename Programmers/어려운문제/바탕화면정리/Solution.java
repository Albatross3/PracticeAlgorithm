package Programmers.어려운문제.바탕화면정리;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

  public static void main(String[] args) {
    System.out.println((int)Math.sqrt(10));
  }
  public int[] solution(String[] wallpaper) {
    int[] answer = new int[4];
    char[][] map = new char[wallpaper.length][wallpaper[0].length()];

    ArrayList<Cor> arr = new ArrayList<>();
    for (int i = 0; i < wallpaper.length; i++) {
      char[] temp = wallpaper[i].toCharArray();
      for (int j = 0; j < temp.length; j++) {
        if (temp[j] == '#') {
          arr.add(new Cor(i, j));
        }
      }
    }

    Collections.sort(arr, (o1, o2) -> o1.x-o2.x);
    // lux, luy, rdx, rdy
    int lux = arr.get(0).x;
    int rdx = arr.get(arr.size() - 1).x + 1;
    
    Collections.sort(arr, (o1, o2) -> o1.y - o2.y);
    int luy = arr.get(0).y;
    int rdy = arr.get(arr.size() - 1).y + 1;

    answer[0] = lux;
    answer[1] = luy;
    answer[2] = rdx;
    answer[3] = rdy;
    return answer;
  }
}
class Cor {
  int x;
  int y;

  public Cor(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
