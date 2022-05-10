package AlgorithmStudy.기하학.P1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int x1,y1,x2,y2;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        T=Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int result1=0;
            int result2=0;

            st=new StringTokenizer(br.readLine());
            x1=Integer.parseInt(st.nextToken());
            y1=Integer.parseInt(st.nextToken());
            x2=Integer.parseInt(st.nextToken());
            y2=Integer.parseInt(st.nextToken());

            n=Integer.parseInt(br.readLine());
            for(int i=0; i<n; i++){
                st=new StringTokenizer(br.readLine());
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                int c=Integer.parseInt(st.nextToken());

                if(isInCircle(a,b,c,x1,y1)==1&&isInCircle(a,b,c,x2,y2)==1){
                    result1+=0;
                    result2+=0;
                }else{
                    result1+=isInCircle(a,b,c,x1,y1);
                    result2+=isInCircle(a,b,c,x2,y2);
                }
            }
            sb.append(result1+result2).append("\n");
        }
        System.out.println(sb);
    }
    public static int isInCircle(int x,int y, int r, int X,int Y){
        int d=(X-x)*(X-x)+(Y-y)*(Y-y);
        if(d>r*r) return 0;
        else return 1;
    }
}
