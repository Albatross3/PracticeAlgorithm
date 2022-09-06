package Programmers.CodingTestKit.해시.완주하지못한선수;

// 해시 - 완주하지 못한 선수
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {

    }
    public static String solution(String[] participant, String[] completion) {
        HashMap<String,Integer> p=new HashMap<>();
        String answer="";
        // {name:count} 구조
        for(String name: participant){
            if(!p.containsKey(name)) p.put(name,1);
            else p.put(name,p.get(name)+1);
        }
        // key에 해당하는 value값을 얻거나 null값을 얻거나 : p.getOrDefault()
        // 완주목록에 있으면 count 값 1 감소
        for(String name:completion){
            p.put(name,p.get(name)-1);
        }
        // count 가 0이 아닌 name 찾기
        for(String name:p.keySet()){
            if(p.get(name)!=0) {
                answer=name;
                break;
            }
        }
        return answer;
    }
}
