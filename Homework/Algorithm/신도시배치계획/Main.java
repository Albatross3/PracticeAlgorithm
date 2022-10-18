package Homework.Algorithm.신도시배치계획;
// Set Cover 문제 - 근사해를 구해야 한다
// 해결책 : Greedy
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, E;
    static ArrayList<Set<Integer>> F;
    static ArrayList<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // U 집합 생성
        Set<Integer> U = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            U.add(i);
        }
        // F 집합 생성 -> ArrayList 인데 들어있는 것은 Set
        F = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            F.add(new HashSet<>());
            F.get(i).add(i);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            F.get(A).add(B);
            F.get(B).add(A);
        }

        while (!U.isEmpty()) {
            // Greedy
            // U의 원소들을 가장 많이 포함하는 집합 Si를 F에서 선택
            int[] tempArr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                Set<Integer> tempU = new HashSet<>(U);
                tempU.retainAll(F.get(i));
                tempArr[i] = tempU.size();
            }
            int tempIndex=0; // 최댓값인 index
            int max = 0;
            for (int i = 1; i <= N; i++) {
                if (tempArr[i] > max) {
                    max = tempArr[i];
                    tempIndex = i;
                }
            }
            // U에서 Si 원소들 제거 -> 차집합
            U.removeAll(F.get(tempIndex));
            // F에서 Si 제거
            F.get(tempIndex).clear();
            // 설치할 위치
            answer.add(tempIndex);
        }
        Collections.sort(answer);
        for (int l : answer) {
            System.out.print(l+" ");
        }

    }
}
