package DAY01.P1759;


import java.util.List;
import java.util.Scanner;
public class Main {

    //공통으로 사용하는 부분 빼놓기
    static int L,C;
    static char[] data;
    static List<String> result;
    public static void main(String[] args) {
        //	System.setIn(new FileInputStream("src/DAY01/Problem1759/input.txt"));
        //값 입력받기
        Scanner sc=new Scanner(System.in);
    }
    static void dfs(int length, int ja, int mo, int current, String pwd) {
//		1.체크인 - 생략가능
//		2. 목적지인가> -길이 +자음,모음 개수
        if (length==L) {
            if(ja>=2 && mo >=1) {
                result.add(pwd);
            }
        }
        else {
//			3. 연결된 곳을 순회 - 나보다 높은 알파벳
            for (int nextIndex=current+1; nextIndex<data.length; nextIndex++) {
                char nextData=data[nextIndex];
//				4.   갈 수 있는가> - 생략 가능
                if(nextData =='a' || nextData=='e') {
//					5.      간다 -dfs(next) ->자음,모음
                    dfs(length+1,ja,mo+1,nextIndex,pwd+nextData);
                }else {
                    dfs(length+1,ja+1,mo,nextIndex,pwd+nextData);
                }
            }


        }
//
//		6. 체크아웃 -생략 가능

    }
}
