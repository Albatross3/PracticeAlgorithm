package AlgorithmStudy.구현.P8958;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String quiz;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());
        for(int x=0; x<N; x++){
            quiz= br.readLine();

            // quiz 점수 1개에 대해
            result=0;
            for(int i=0; i<quiz.length(); i++){
                for(int j=i; j>=0; j--){
                    if(quiz.charAt(j)=='O'){
                        result++;
                    }else{
                        break;
                    }
                }
            }
            sb.append(result+"\n");
        }
        System.out.println(sb);
    }
}
