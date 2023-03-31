package AlgorithmStudy.이분탐색.P2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] tree;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    tree = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      tree[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(tree);
    System.out.println(binarySearch(tree));

  }

  public static int binarySearch(int[] array) {
    int lo = 0, hi = array[array.length - 1];
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (remainSum(array, mid) >= M) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }

  public static long remainSum(int[] array, int cut) {
    long sum = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] > cut) {
        sum += array[i]-cut;
      }
    }
    return sum;
  }
}
