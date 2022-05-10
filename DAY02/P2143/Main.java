package DAY02.P2143;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static int n,m;
    static int[] A;
    static int[] B;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        T=Integer.parseInt(br.readLine());
        n=Integer.parseInt(br.readLine());


        A=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            A[i]=Integer.parseInt(st.nextToken());

//        System.out.println(T);
//        System.out.println(n);
//        for(int i=0; i<n; i++)
//            System.out.print(A[i]+" ");

        m=Integer.parseInt(br.readLine());
        B=new int[m];
        st=new StringTokenizer(br.readLine());

        for(int i=0; i<m; i++)
            B[i]=Integer.parseInt(st.nextToken());

        List<Long> subA=new ArrayList<>();
        List<Long> subB=new ArrayList<>();

        //subA 구하기
        for(int i=0; i<n; i++){
            long sum=A[i];
            subA.add(sum);
            for(int j=i+1; j<n; j++){
                sum+=A[i];
                subA.add(sum);
            }
        }
        //subB 구하기
        for(int i=0; i<m; i++){
            long sum=B[i];
            subB.add(sum);
            for(int j=i+1; j<m; j++){
                sum+=B[i];
                subB.add(sum);
            }
        }
        Collections.sort(subA); //오름차순(기본)
        Collections.sort(subB, Comparator.reverseOrder()); //내림차순 정렬

        long result=0;
        int ptA=0;
        int ptB=0;
        while(ptA < subA.size() && ptB < subB.size()){
            long currentA = subA.get(ptA);
            long target = T-currentA; // subB에서 찾고 싶은 target값
            // currentB==target -> subA,subB 같은 수 개수 체크 -> 답 구하기 ptA++,ptB++
            if (subB.get(ptB) == target){
                long countA=0;
                long countB=0;
                //전체 사이즈보다 ptA가 작게
                while(ptA<subA.size() && subA.get(ptA) == currentA){
                    countA++;
                    ptA++;
                }
                while(ptB<subB.size() && subB.get(ptB) == target){
                    countB++;
                    ptB++;
                }
                result += countA*countB;
            }
            // currentB > target -> ptB++
            else if(subB.get(ptB) > target){
                ptB++;
            }
            // currentB < target -> ptA++
            else {
                ptA++;
            }
        }
        System.out.println(result);
    }
}
