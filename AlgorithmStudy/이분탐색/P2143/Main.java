package AlgorithmStudy.이분탐색.P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int T;
  static int n, m;
  static int[] A;
  static int[] B;

  public static void main(String[] args) throws IOException, InterruptedException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    T = Integer.parseInt(br.readLine());
    n = Integer.parseInt(br.readLine());
    A = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
    m = Integer.parseInt(br.readLine());
    B = new int[m];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }

    // 1. A,B의 부분합을 구하기
    ArrayList<Integer> setSumA = getSetOfPrefixSum(A);
    ArrayList<Integer> setSumB = getSetOfPrefixSum(B);

    // 2. 정렬하기
    Collections.sort(setSumA);
    Collections.sort(setSumB);

    // 3. A for 문 돌면서 B 에서 T-A[i] 값 이분 탐색하기
    long count = 0;
    for (int i = 0; i < setSumA.size(); i++) {
      int target = T - setSumA.get(i);
      count += upperBound(setSumB, target) - lowerBound(setSumB, target);
    }
    System.out.println(count);
  }

  public static ArrayList<Integer> getSetOfPrefixSum(int[] array) {
    ArrayList<Integer> set = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      int tempSum = array[i];
      set.add(tempSum);
      for (int j = i + 1; j < array.length; j++) {
        tempSum += array[j];
        set.add(tempSum);
      }
    }
    return set;
  }

  public static int lowerBound(ArrayList<Integer> array, int target) {
    int lo = -1, hi = array.size();
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!(array.get(mid) >= target)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }

  public static int upperBound(ArrayList<Integer> array, int target) {
    int lo = -1, hi = array.size();
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!(array.get(mid) > target)) lo = mid;
      else hi = mid;
    }
    return hi;
  }
}
