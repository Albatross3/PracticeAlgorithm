package AlgorithmStudy.그리디알고리즘.P2839;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// 그리디 알고리즘 - 설탕배달
// 다이나믹 프로그래밍으로도 가능한 듯 - >Refactroing
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        int limit=N/3;
        boolean isExist=false;
        for(int i=0; i<=limit; i++){
            if((N-3*i)%5==0){
                isExist=true;
                System.out.println(i+(N-3*i)/5);
                break;
            }
        }
        if(isExist==false){
            System.out.println(-1);
        }


    }
}
