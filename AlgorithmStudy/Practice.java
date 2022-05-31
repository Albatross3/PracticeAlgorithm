package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Practice {
    static Set<String> A=new HashSet<>();
    static Set<String> B=new HashSet<>();
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++)
            A.add(br.readLine());
        for(int i=0; i<M; i++)
            B.add(br.readLine());
        A.retainAll(B);

        List<String> arr=new ArrayList<>(A);
        Collections.sort(arr);

        sb.append(arr.size()).append("\n");
        for(String s:arr)
            sb.append(s).append("\n");
        System.out.println(sb);
    }
}
