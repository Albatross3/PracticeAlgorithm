package DataStructure.힙;

// P1927

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        MinHeap minHeap = new MinHeap();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            // 최소힙 삭제
            if (x == 0) {
                sb.append(minHeap.poll()).append("\n");
            }
            // 최소힙 추가
            else {
                minHeap.add(x);
            }
        }
        System.out.println(sb);
    }
}
class MinHeap {
    List<Integer> list;

    public MinHeap() {
        list = new ArrayList<>();
        list.add(0); // 실제 사용 index 는 1부터
    }

    // 추가 메서드
    public void add(int x) {
        // 마지막에 추가
        list.add(x);

        int currentIndex = list.size() - 1;
        // 부모와 비교해서 작으면 올라가기 크면 멈추기
        while (list.get(currentIndex / 2) > list.get(currentIndex) && currentIndex!=1) {
            int temp = list.get(currentIndex / 2);
            list.set(currentIndex / 2, list.get(currentIndex));
            list.set(currentIndex, temp);
            currentIndex /= 2;
        }

    }

    // 삭제 메서드
    public int poll() {
        // 1. 1번 index 값 꺼내기
        if(list.size()==1) return 0;
        int minValue = list.get(1);

        // 2. 마지막 값을 1번 index 로 바꾸기 + 마지막 값 제거
        int lastValue = list.get(list.size() - 1);
        list.set(1, lastValue);
        list.remove(list.size() - 1);
        // 3. 루트 노드(1번 인덱스) 부터 시작해서 자식과 비교 -> 자식 보다 크면 swap (자식 중 더 작은 값과 swap)
        int currentIndex = 1;
        int lastIndex = list.size() - 1;
        while (true) {
            // 자식이 없는 경우
            if (currentIndex * 2 > lastIndex) {
                break;
            }
            // 자식이 왼쪽만 있는 경우
            else if (currentIndex * 2 == lastIndex) {
                int parentValue = list.get(currentIndex);
                int childValue = list.get(currentIndex * 2);
                // 부모가 자식보다 큰 경우 -> swap
                if (parentValue > childValue) {
                    list.set(currentIndex, childValue);
                    list.set(currentIndex * 2, parentValue);
                    currentIndex *= 2;
                } else {
                    break;
                }
            }
            // 자식이 2명 모두 있는 경우
            else {
                int leftChildValue = list.get(currentIndex * 2);
                int rightChildValue = list.get(currentIndex * 2 + 1);

                int parentValue = list.get(currentIndex);
                // 왼쪽이 더 작은 경우
                if (leftChildValue <= rightChildValue) {
                    if (parentValue > leftChildValue) {
                        list.set(currentIndex, leftChildValue);
                        list.set(currentIndex * 2, parentValue);
                        currentIndex *= 2;
                    } else {
                        break;
                    }
                }
                // 오른쪽이 더 작은 경우
                else {
                    if (parentValue > rightChildValue) {
                        list.set(currentIndex, rightChildValue);
                        list.set(currentIndex * 2 + 1, parentValue);
                        currentIndex = currentIndex * 2 + 1;
                    } else {
                        break;
                    }
                }

            }
        }
        return minValue;
    }
}
