package AlgorithmStudy.그리디알고리즘.P1931;

// 정렬 + 그리디
// 종료시간을 기준으로 정렬하며 종료시간이 빠를 수록 선택범위가 많다는 것
// 으로부터 그리디 적용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        arr=new int[N][2];
        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        // 종료시간 기준 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return o1[0]-o2[0];
                else return o1[1]-o2[1];
            }
        });
        // 그리디 알고리즘
        int count=1;
        int standard=arr[0][1];
        for(int i=1; i<N; i++){
           if(standard<=arr[i][0]){
                standard=arr[i][1];
                count++;
           }
        }
        System.out.println(count);
    }
}
