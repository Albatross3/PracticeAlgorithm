package AlgorithmStudy.정렬.P8979;

// 백준 - 올림픽
// 비교했을 때 같은 경우에 대해 0이 되는 부분을 반드시 구현했어야 했다
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static List<Nation> arr=new ArrayList<>();
    static int index;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int g=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            arr.add(new Nation(n,g,s,b,0));
        }
        Collections.sort(arr);
        arr.get(0).setRank(1);

        // 등수 부여
        for(int i=1; i<arr.size(); i++){
            Nation n=arr.get(i-1);
            Nation n2=arr.get(i);
            if(n.gold==n2.gold && n.silver==n2.silver && n.bronze==n2.bronze){
                n2.setRank(n.rank);
            }
            else{
                n2.setRank(i+1);
            }
            if (n2.nationNumber==K) {
                index = i;
                break;
            }
        }
        System.out.println(arr.get(index).rank);
    }
}
class Nation implements Comparable<Nation>{
    int nationNumber;
    int gold,silver,bronze;
    int rank;
    public Nation(int nationNumber,int gold,int silver, int bronze, int rank){
        this.nationNumber=nationNumber;
        this.gold=gold;
        this.silver=silver;
        this.bronze=bronze;
        this.rank=rank;
    }
    @Override
    public int compareTo(Nation o){
        if(this.gold < o.gold) return 1;
        else if(this.gold==o.gold) {
            if(this.silver < o.silver) return 1;
            else if(this.silver == o.silver) {
                if(this.bronze < o.bronze ) return 1;
                else if(this.bronze==o.bronze) return 0;
            }
        }
        return  -1;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}