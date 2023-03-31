package AlgorithmStudy.투포인터.P2143;

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
  public static void main(String[] args) throws IOException {
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

    // 누적합 집합 만들기
    ArrayList<Integer> setA = getSetOfPrefixSum(A);
    ArrayList<Integer> setB = getSetOfPrefixSum(B);

    // A 는 오름차순, B는 내림차순 정렬
    Collections.sort(setA);
    Collections.sort(setB, Collections.reverseOrder());

    // 투 포인터 활용
    long count = 0;
    int ptA = 0;
    int ptB = 0;
    while (ptA >= 0 && ptA < setA.size() && ptB >= 0 && ptB < setB.size()) {
      int currentA = setA.get(ptA);
      int target = T - currentA;
      if (setB.get(ptB) > target) {
        ptB++;
      } else if (setB.get(ptB) < target) {
        ptA++;
      }
      else {
        long countA = 0;
        long countB = 0;
        // A 집합 내에서 같은 값들 가져오기
        while (ptA < setA.size() && setA.get(ptA) == currentA) {
          countA++;
          ptA++;
        }

        // B 집합 내에서 같은 값들 가져오기
        while (ptB < setB.size() && setB.get(ptB) == target) {
          countB++;
          ptB++;
        }
        count += countA * countB;
      }
    }
    System.out.println(count);
  }

  public static ArrayList<Integer> getSetOfPrefixSum(int[] array) {
    ArrayList<Integer> arr = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      int sum = 0;
      for (int j = i; j < array.length; j++) {
        sum += array[j];
        arr.add(sum);
      }
    }
    return arr;
  }
}
