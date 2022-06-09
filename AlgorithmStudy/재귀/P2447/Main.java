package AlgorithmStudy.재귀.P2447;

// P2447
// 분할 정복 - 별 찍기 - 10
// 배열을 사용하는 이유는 정확한 위치를 지정하기 위해서
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        N=Integer.parseInt(br.readLine());
        arr=new char[N][N];
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                arr[i][j]=' ';
            }
        }
        printStar(0,0,N);
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void printStar(int x,int y,int n){
        if(n==1) arr[x][y]='*';
        if(n>1){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(!(i==1 && j==1)) printStar(x+i*(n/3),y+j*(n/3),n/3);
                }
            }
        }
    }
}
