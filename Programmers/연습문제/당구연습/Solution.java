package Programmers.연습문제.당구연습;

// 예외 케이스에 대해 있는지 파악하는 능력 필요
public class Solution {
  public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
    int length = balls.length;
    int[] answer = new int[length];
    int[] start = new int[]{startX, startY};
    for (int i = 0; i < length; i++) {
      int distanceByDownSymmetry = getDistance(start, new int[]{balls[i][0], -balls[i][1]});
      int distanceByUpSymmetry = getDistance(start, new int[]{balls[i][0], 2 * n - balls[i][1]});
      int distanceByLeftSymmetry = getDistance(start, new int[]{-balls[i][0], balls[i][1]});
      int distanceByRightSymmetry = getDistance(start, new int[]{2 * m - balls[i][0], balls[i][1]});

      if (start[0] == balls[i][0]) {
        if (start[1] < balls[i][1]) {
          distanceByUpSymmetry = Integer.MAX_VALUE;
        } else if (start[1] > balls[i][1]) {
          distanceByDownSymmetry = Integer.MAX_VALUE;
        }
      } else if (start[1] == balls[i][1]) {
        if (start[0] < balls[i][0]) {
          distanceByRightSymmetry = Integer.MAX_VALUE;
        } else if (start[0] > balls[i][0]) {
          distanceByLeftSymmetry = Integer.MAX_VALUE;
        }
      }
      answer[i] = Math.min(Math.min(distanceByDownSymmetry, distanceByUpSymmetry),
          Math.min(distanceByLeftSymmetry, distanceByRightSymmetry));
    }

    return answer;
  }

  public static int getDistance(int[] cor1, int[] cor2) {
    int diffX = cor1[0] - cor2[0];
    int diffY = cor1[1] - cor2[1];
    return diffX * diffX + diffY * diffY;
  }
}
