package AlgorithmStudy.백트래킹.P14889;

// 스타트와 링크
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] S;
    static int[] number;
    static boolean[] isUsed;
    static int Min=Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        S = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        number=new int[N/2+1];
        isUsed = new boolean[N + 1];
        combination(1);
        System.out.println(Min);

    }

    public static void combination(int n) {
        if(n==N/2+1) {
            findMin();
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!isUsed[i] && number[n - 1] < i) {
                number[n] = i;
                isUsed[i]=true;
                combination(n+1);
                isUsed[i]=false;
            }
        }
    }

    public static void findMin(){
        int startValue=0;
        int linkValue=0;

        for(int i=1; i<N; i++){
            for (int j = i + 1; j < N + 1; j++) {
                if(isUsed[i] && isUsed[j]){
                    startValue+=(S[i][j]+S[j][i]);
                } else if (!isUsed[i] && !isUsed[j]) {
                    linkValue += (S[i][j] + S[j][i]);
                }
            }
        }

        Min = Integer.min(Min, Math.abs(startValue - linkValue));
    }
}
