package AlgorithmStudy.그리디알고리즘.P13305;

// 1. price가 전체적으로 감소하도록 선택해야 한다
// 2. 자료형 long
// 3. Refactoring 더 간단하게 코드 짜기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] length;
    static long[] price;

    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());

        // 도시 거리
        length=new long[N-1];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++){
            length[i]=Long.parseLong(st.nextToken());
        }

        // 리터당 가격
        price=new long[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            price[i]=Long.parseLong(st.nextToken());
        }

        // 선택된 도시들
        isSelected=new boolean[N];
        isSelected[0]=true;
        int point=0;
        for(int i=0; i<N-1; i++){
            if(price[i+1]<price[point]){
                point=i+1;
                isSelected[i+1]=true;
            }
        }

        for(int i=0; i<N; i++){
            System.out.print(isSelected[i]+" ");
        }


        // true에서의 price*true 부터 시작해서 다음 true 까지의 길이 누적 합
        long result=0;
        int i=0;
        while(i!=N-1){
            if(isSelected[i]==true){
                int index=i;
                while(true){
                    result+=price[index]*length[i];
                    i++;
                    if(isSelected[i]==true || i==N-1) break;
                }
            }
        }
        System.out.println(result);
    }
}
