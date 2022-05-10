package AlgorithmStudy.빡구현.P17281;

// 백준 야구문제
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] score;
    static List<String> battingOrder=new ArrayList<>(40320);
    static boolean[] isVisited=new boolean[10];
    static int[] tempResult=new int[8];
    static int count=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        score=new int[N][9];
        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                score[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        getOrder(0);

        int result=0;
        int[] tempScore=new int[9];
        // 모든 타순에 대한 경우의 수
        for(int c=0; c<count; c++){
            char[] order=battingOrder.get(c).toCharArray();
            // 각 타순에 대한 점수 결과
            int tempResult=0;
            int nextPlayer=0;
            for(int i=0; i<N; i++){
                for(int x=0; x<9; x++){
                    // temp : 선수 번호
                    int temp= order[x]-'0';
                    tempScore[x]=score[i][temp-1];
                }
                // 각 이닝에서의 득점 계산
                int out=0;
                boolean[] runner=new boolean[4];
                while(out!=3){
                    if(tempScore[nextPlayer]==0) {
                        out += 1;
                    }
                    else if(tempScore[nextPlayer]==1){
                        for(int index=3; index>0; index--){
                            if(runner[index]) {
                                runner[index] = false;
                                if(index+1 >=4) tempResult+=1;
                                else runner[index+1]=true;
                            }
                        }
                        runner[1]=true;
                    }else if(tempScore[nextPlayer]==2){
                        for(int index=3; index>0; index--){
                            if(runner[index]) {
                                runner[index] = false;
                                if(index+2 >=4) tempResult+=1;
                                else runner[index+2]=true;
                            }
                        }
                        runner[2]=true;
                    }else if(tempScore[nextPlayer]==3){
                        for(int index=3; index>0; index--){
                            if(runner[index]) {
                                runner[index] = false;
                                tempResult+=1;
                            }
                        }
                        runner[3]=true;
                    }else{
                        for(int index=3; index>0; index--){
                            if(runner[index]) {
                                runner[index] = false;
                                tempResult+=1;
                            }
                        }
                        tempResult+=1;
                    }

                    nextPlayer=(nextPlayer+1)%9;
                }

            }
            result=Math.max(result,tempResult);
        }
        System.out.println(result);
    }
    // 1. 타순을 백트래킹으로 구현
    public static void getOrder(int level){
        if(level==8){
            StringBuilder sb=new StringBuilder();
            for(int i=0; i<3; i++){
                sb.append(tempResult[i]);
            }
            sb.append(1);
            for(int i=3; i<8; i++){
                sb.append(tempResult[i]);
            }
            battingOrder.add(sb.toString());
            count++;
        }
        for(int i=2; i<=9; i++){
            if(!isVisited[i]){
                tempResult[level]=i;
                isVisited[i]=true;
                getOrder(level+1);
                isVisited[i]=false;
            }
        }
    }
}
