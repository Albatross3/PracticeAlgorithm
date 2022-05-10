//import java.util.*;
//import java.io.*;
//
//class Main {
//    static int N,T;
//    static String[] K;
//    static String[] W;
//
//    public static void main(String args[]) throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb=new StringBuilder();
//        // 입력
//        N=Integer.parseInt(br.readLine());
//        K=new String[N];
//        for(int i=0; i<N; i++){
//            K[i]=br.readLine();
//        }
//        T=Integer.parseInt(br.readLine());
//        W=new String[T];
//        for(int i=0; i<T; i++){
//            W[i]=br.readLine();
//        }
//        //
//        for(int i=0; i<T; i++){
//            int temp=0;
//            for(int j=0; j<N; j++){
//                if(K[j].contains(W[i])) {
//                    temp++;
//                }
//            }
//            sb.append(temp).append("\n");
//        }
//        System.out.println(sb);
//    }
//}