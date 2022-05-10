package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Programmers {
    static int N;
    static int[] stages;
    static List<fail> arr=new ArrayList<fail>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        stages=new int[8];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<8; i++){
            stages[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(stages);

        int count=0;
        int temp;
        for(int i=1; i<=N; i++){
            int right=count_right(stages,i);
            int left=count_left(stages,i);
            if(right==-1) temp=0;
            else temp=right-left+1;
            arr.add(new fail(i,(double)temp/(stages.length-count)));
            count+=temp;
        }
        Collections.sort(arr);
        // 출력 부분
        for(int i=0; i<N; i++){
            System.out.print(arr.get(i).getIndex()+" ");
        }
    }
    // 왼쪽 끝 index 구하기
    public static int count_left(int[] array, int target){
        int start=0; int end= array.length;
        while(start<=end){
            int middle=(start+end)/2;
            if(array[middle]==target && (middle==0 || array[middle-1]!=target)) return middle;
            else if(array[middle] > target) end=middle-1;
            else start=middle+1;
        }
        // 못 찾은 경우
        return -1;
    }
    // 오른쪽 끝 index 구하기
    public static int count_right(int[] array,int target){
        int start=0; int end= array.length;
        while(start<=end){
            int middle=(start+end)/2;
            if(array[middle]==target && (middle==array.length-1 || array[middle+1]!=target)) return middle;
            else if(array[middle] > target) end=middle-1;
            else start=middle+1;
        }
        // 못 찾은 경우
        return -1;
    }
}
class fail implements Comparable<fail>{
    int index;
    double rateFailure;

    public fail(int index, double rateFailure){
        this.index=index;
        this.rateFailure=rateFailure;
    }
    public int getIndex(){
        return index;
    }
    @Override
    public int compareTo(fail o) {
        if(this.rateFailure < o.rateFailure) return 1;
        else if(this.rateFailure==o.rateFailure) {
            if(this.index > o.index)
                return 1; // 양수면 자리 바꾼다
        }
        return -1;
    }
}