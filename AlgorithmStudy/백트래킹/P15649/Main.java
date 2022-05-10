package AlgorithmStudy.백트래킹.P15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] result;
    static boolean[] isUsed;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st=new StringTokenizer(br.readLine());
       N=Integer.parseInt(st.nextToken());
       M=Integer.parseInt(st.nextToken());
       result=new int[M+1];
       isUsed=new boolean[N+1];
       permutation(1);
       System.out.println(sb);
    }
    public static void permutation(int n){
        if(n==M+1){
            for(int i=1; i<=M; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=1; i<=N; i++){
            if(!isUsed[i] ) {
                result[n] = i;
                isUsed[i] = true;
                permutation(n+1);
                isUsed[i] = false;
            }
        }
    }

}
