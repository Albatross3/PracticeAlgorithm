package AlgorithmStudy.구현.P4673;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    static boolean[] isNotSelf;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        isNotSelf=new boolean[10001];

        for(int i=1; i<=10000; i++){
            int d=generator(i);
            if(d<=10000) {
                isNotSelf[d] = true;
            }
        }
//        StringBuilder sb=new StringBuilder();
//        for(int i=1; i<=10000; i++){
//            if(!isNotSelf[i]){
//                sb.append(i).append("\n");
//            }
//        }
//        System.out.println(sb);
        for(int i=1; i<=10000; i++){
            if(!isNotSelf[i]){
                bw.write(i+"\n");
            }
        }
        bw.flush();
        bw.close();

    }
    static int generator(int n){
        int result=n;
        while(n!=0){
            result+=n%10;
            n=n/10;
        }
        return result;
    }
}
