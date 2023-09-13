package Baekjoon.재귀.P1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1256 문제와 같은 방식으로 처음 풀이한 방식
public class Main2 {
    static int N;
    static int problemNumber;
    static long k;

    static long[] factorial;
    static boolean[] isVisited;
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        problemNumber = Integer.parseInt(st.nextToken());


        factorial = new long[N + 1];
        factorial[0] = 1;
        factorial[1] = 1;
        for (int i = 2; i < N + 1; i++) {
            factorial[i] = i * factorial[i - 1];
        }

        isVisited = new boolean[N + 1];

        if (problemNumber == 1) {
            k = Long.parseLong(st.nextToken());
            String permutation = query(0, k, "");
            System.out.println(permutation);
        } else if (problemNumber == 2) {
            long totalOrder = 1;
            number = new int[N + 1];
            for (int i = 1; i < number.length; i++) {
                number[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i < number.length; i++) {
                int order = getOrder(number[i]);

                totalOrder += (order - 1) * factorial[N - i];
            }
            System.out.println(totalOrder);
        }
    }
    public static int getOrder(int number) {
        int count = 0;
        for (int i = 1; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                count++;
                if (i == number) {
                    isVisited[number] = true;
                    return count;
                }
            }
        }
        return 0;
    }
    public static String query(int depth, long order, String answer) {
        // return 조건
        int seekCount = N - depth;
        if (seekCount == 1) {
            return answer + findNumberByOrder(1);
        }
        for (int i = 1; i <= seekCount; i++) {
            if (order <= i * factorial[seekCount-1]) {
                int findNumber = findNumberByOrder(i);
                return query(depth + 1, order - (i - 1) * factorial[seekCount - 1], answer + findNumber + " ");
            }
        }
        return "";
    }

    public static int findNumberByOrder(int order) {
        int count = 0;
        for (int i = 1; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                count++;
                if(count == order) {
                    isVisited[i] = true;
                    return i;
                }
            }
        }
        return 0;
    }

}
