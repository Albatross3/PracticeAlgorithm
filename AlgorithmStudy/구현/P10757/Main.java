package AlgorithmStudy.구현.P10757;


// StringBuilder 의 reverse() 함수 이용하면 쉽게 거꾸로 뒤집을 수 있다
// char -> int 형 변환
//      1. 문자 - 'a' or 'A'
//      2. 숫자 - '0'
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String stringA,stringB;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        StringBuilder out=new StringBuilder();
        stringA=st.nextToken();
        stringB=st.nextToken();

        // BigInteger 쓰지 않고 풀이하는 법
        int lenA=stringA.length();
        int lenB=stringB.length();

        // 길이가 짧은 쪽에 "0" 을 차이만큼 더해주기
        if(lenA>=lenB){
            for(int i=0; i<lenA-lenB; i++){
                sb.append("0");
            }
            stringB=sb.toString()+stringB;

        }else{
            for(int i=0; i<lenB-lenA; i++){
                sb.append("0");
            }
            stringA=sb.toString()+stringA;
        }

        // 더하는 알고리즘
        lenA=stringA.length();
        int q=0;
        for(int i=lenA-1; i>=0; i--){
            int subSum=q+(stringA.charAt(i)-'0')+(stringB.charAt(i)-'0');
            int r=subSum%10;
            q=subSum/10;
            out.append(r);
        }

        // 마지막에 몫이 있으면 추가해주기
        if(q==0) {
            System.out.println(out.reverse());
        }
        else{
            out.append(q);
            System.out.println(out.reverse());
        }

    }
}
