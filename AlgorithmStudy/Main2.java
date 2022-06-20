package AlgorithmStudy;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main2 {
    static int numA,numB;
    static Set<Integer> A=new HashSet<>();
    static Set<Integer> B=new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        numA=Integer.parseInt(st.nextToken());
        numB=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<numA; i++){
            A.add(Integer.parseInt(st.nextToken()));
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<numB; i++){
            B.add(Integer.parseInt(st.nextToken()));
        }
        Set<Integer> newA=new HashSet<>(A);
        A.removeAll(B);
        B.removeAll(newA);
        System.out.println(A.size()+B.size());
    }
}
