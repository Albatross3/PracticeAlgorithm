package Programmers.CodingTestKit.완전탐색.소수찾기;

// 길이를 다양하게 뽑기 위해서 backtracking 을 여러번 돌리면 된다
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("011"));
    }

    static Set<Integer> set = new HashSet<>();
    static boolean[] isVisited;
    static String[] answer;
    public int solution(String numbers) {
        int count=0;
        int length=1;
        for (int i = 0; i < numbers.length(); i++) {
            isVisited = new boolean[numbers.length()];
            answer = new String[length];
            backtracking(0,length++,numbers);
        }
        System.out.println(set);
        for (int num : set) {
            if(isPrime(num)) count++;
        }
        return count;
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number%i==0) return false;
        }
        return true;
    }

    public static void backtracking(int depth,int target,String numbers) {
        if (depth == target ) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < answer.length; i++) {
                sb.append(answer[i]);
            }
            set.add(Integer.parseInt(sb.toString()));
            return;
        }
        for (int i = 0; i < numbers.length(); i++) {
            if (!isVisited[i]) {
                isVisited[i]=true;
                answer[depth] = Character.toString(numbers.charAt(i));
                backtracking(depth+1,target,numbers);
                isVisited[i]=false;
            }
        }
    }
}
