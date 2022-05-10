//package DAY03.P2042;
//
//import java.io.*;
//import java.util.StringTokenizer;
//
//public class Main {
//    static int N,M,K;
//    static long[] X;
//
//    public static void main(String[] args) throws IOException {
//        //indexed tree 문제 반드시 풀어볼 것
//        System.setIn(new FileInputStream("src/DAY03/P2042/input.txt"));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st=new StringTokenizer(br.readLine());
//
//        N=Integer.parseInt(st.nextToken());
//        M=Integer.parseInt(st.nextToken());
//        K=Integer.parseInt(st.nextToken());
//
//        X=new long[N];
//        for(int i=0; i<N; i++){
//            X[i]=Long.parseLong(br.readLine());
//        }
//        int S=1;
//        while(S < N){
//            S*=2;
//        }
//
//        long[] indexedTree=new long[2*S];
//        //S 부터 2S-1 까지
//       for (int i=0; i<N; i++) {
//           indexedTree[S+i] = X[i];
//
//       }
//       for (int i=S+N; i<2*S; i++){
//           indexedTree[i]=0;
//       }
//        //indexed tree 완성
//       for(int i=S-1; i>0; i--) {
//           indexedTree[i] = indexedTree[2 * i] + indexedTree[2 * i + 1];
//       }
//
//
//    }
//    static void update(int left,int right,int node,int index, long diff){
//        if(left <=index&& index <=right){
//            tree[node]+=diff;
//            if(left!=right){
//                int mid=(left+right)/2;
//                update(left,mid,node*2,index,diff);
//                update(mid+1,right,node*2+1,index,diff);
//            }
//        }
//
//    }
//    static int query(int left,int right,int node, int count){
//        //1.leaf 에 도착했을 때 -> 사탕 번호 반환
//        if(left ==right){
//            return left;
//        }
//        int mid= (left+right)/2;
//        //2.왼쪽 >= count ->왼쪽으로 이동
//        if(tree[node*2] >= count){
//            return query(left,mid, node*2 ,count);
//        }
//        //3.왼쪽 < count
//        else {
//            return query(mid+1,right ,node*2+1, count-tree[node*2]);
//        }
//    }
//}
