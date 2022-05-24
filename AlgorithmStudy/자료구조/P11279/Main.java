package AlgorithmStudy.자료구조.P11279;

// MaxHeap 자료구조 (우선순위 큐)
// ArrayList에서 add(index, data) 는 index 자리에 data를 넣어주게 되서 뒤에 있는 것들이 밀려난다
// 즉 변경을 하고 싶다면 set(index,data) 메소드를 사용해야 한다
// refactoring 필요

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());
        MaxHeap maxHeap=new MaxHeap();
        for(int i=0; i<N; i++){
            int x=Integer.parseInt(br.readLine());
            if(x==0) sb.append(maxHeap.delete()).append("\n");
            else maxHeap.insert(x);
        }
        System.out.println(sb);
    }

}
class MaxHeap{
    int last;
    ArrayList<Integer> arr;
    public MaxHeap(){
        last=0;
        arr=new ArrayList<>();
        arr.add(0); // 0번째는 사용안함
    }
    // 삽입
    public void insert(int n){
        // 1. 새로운 노드에 추가
        arr.add(n);
        last++;
        // 2. 부모 노드와 비교하여 maxHeap 유지
        int temp=last;
        while(temp!=1 && arr.get(temp/2) < arr.get(temp)){
            int tempValue = arr.get(temp);
            arr.set(temp, arr.get(temp / 2));
            arr.set(temp / 2, tempValue);
            temp = temp / 2;
        }
    }
    // 삭제
    public int delete(){
        if(last==0) return 0;
        else{
            int answer=arr.get(1);
            // 1. 마지막 노드 제거
            int removedValue=arr.remove(last);
            last--;
            if(arr.size()>=2) {
                arr.set(1, removedValue);
                // 2. 자식 노드와 비교 ( 자식 노드 값 중 큰 값과 교환 )
                int temp = 1;
                while (temp <= last) {
                    int tempValue = arr.get(temp);
                    // 자식 노드 없는 경우
                    if (temp * 2 > last) break;
                        // 자식 노드 1개
                    else if (temp * 2 == last) {
                        int left = arr.get(temp * 2);
                        if (tempValue < left) {
                            arr.set(temp, left);
                            arr.set(temp * 2, tempValue);
                            temp = temp * 2;
                        } else break;
                    }
                    // 자식 노드 2개
                    else {
                        int left = arr.get(temp * 2);
                        int right = arr.get(temp * 2 + 1);
                        if (tempValue < left || tempValue < right) {
                            if (left > right) {
                                arr.set(temp, left);
                                arr.set(temp * 2, tempValue);
                                temp = temp * 2;
                            } else {
                                arr.set(temp, right);
                                arr.set(temp * 2 + 1, tempValue);
                                temp = temp * 2 + 1;
                            }
                        } else break;
                    }

                }
            }
            return answer;
        }
    }
}
