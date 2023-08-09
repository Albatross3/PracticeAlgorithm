package Baekjoon.그리디알고리즘.P20070;
// 11:20 ~ 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Date> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr.add(new Date(s, e, e - s));
        }
        // 시작 빠를수록, 같으면 길이가 긴 일정 먼저 오도록 정렬
        arr.add(new Date(367, 367,0));
        Collections.sort(arr);

        //

        int sum = 0;

        PriorityQueue<Integer> endList = new PriorityQueue<>();
        endList.add(arr.get(0).end);
        int start = arr.get(0).start;
        int end = arr.get(0).end;
        for (int i = 1; i < arr.size(); i++) {
            // 중복이 되는 경우
            if (arr.get(i).start <= end+1) {
                // 넣을 줄 찾고 넣기
                // 찾기
                PriorityQueue<Integer> temp = new PriorityQueue<>();
                while (!endList.isEmpty()) {
                    int tempEnd = endList.poll();
                    if (tempEnd < arr.get(i).start) {
                        endList.add(arr.get(i).end);
                        end = Math.max(end, arr.get(i).end);
                        break;
                    }
                    temp.add(tempEnd);
                }
                // 넣기
                if (endList.isEmpty()) {
                    endList.addAll(temp);
                    endList.add(arr.get(i).end);
                    end = Math.max(end, arr.get(i).end);
                } else {
                    endList.addAll(temp);
                }

            } else {
                sum += (end - start+1) * endList.size();
                endList.clear();
                // start, end , endList update
                start = arr.get(i).start;
                end = arr.get(i).end;
                endList.add(end);
            }
        }
        System.out.println(sum);

    }


}
class Date implements Comparable<Date>{
    int start;
    int end;
    int length;

    public Date(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    @Override
    public int compareTo(Date o) {
        if (this.start == o.start) {
            return o.length - this.length;
        }
        else return this.start - o.start;
    }
}
