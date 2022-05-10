package DevMatching.P2;

public class Main {
    public static void main(String[] args) {
        String ar;
        double r=Math.pow(2,3);
        System.out.println(r);
    }
}
//import java.util.*;
//class Solution {
//    static int[] dy={-1,0,1,0};
//    static int[] dx={0,1,0,-1};
//    static char[][] map;
//    static boolean[][] visited;
//
//    static char[] target={'a','b','c'};
//
//    static int answer=0;
//    static int numQ=0;
//    static char[] arr;
//    public int solution(String[] grid) {
//        map=new char[grid.length][grid[0].length()];
//        for(int i=0; i<grid.length; i++){
//            for(int j=0; j<grid[0].length(); j++){
//                char temp=grid[i].charAt(j);
//                map[i][j]=temp;
//                if (temp=='?') numQ++;
//            }
//        }
//        arr=new char[numQ];
//        for(int i=0; i<(int)Math.pow(3,numQ); i++){
//
//            // 물음표를 조합으로 채우기
//            for(int i=0; i<grid.length; i++){
//                for(int j=0; j<grid[0].length(); j++){
//
//                }
//            }
//        }
//
//        return numQ;
//    }
//    public static void permutation(int count){
//        if(count==numQ){
//            return;
//        }
//        for(int i=0; i<3; i++){
//            arr[count]=target[i];
//            permutation(count+1);
//        }
//    }
//}