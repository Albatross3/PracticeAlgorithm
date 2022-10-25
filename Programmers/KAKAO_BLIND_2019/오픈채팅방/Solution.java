package Programmers.KAKAO_BLIND_2019.오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 문자열 + 연산 10만번 가능할까?
public class Solution {
    public String[] solution(String[] record) {
        ArrayList<Nickname> who = new ArrayList<>(); // 어떤 id가 들어오고 나갔는지
        ArrayList<String> inOrOut = new ArrayList<>(); // 들어왔습니다 or 나갔습니다
        Map<String, Nickname> map = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
            StringTokenizer st = new StringTokenizer(record[i]);
            String order = st.nextToken();
            String id = st.nextToken();
            switch (order) {
                case "Enter":
                    String nickname = st.nextToken();
                    inOrOut.add(" 들어왔습니다.");

                    // 이미 있는 경우 -> 닉네임 바꾸기
                    if (map.containsKey(id)) {
                        map.get(id).change(nickname);
                        who.add(map.get(id));
                    }
                    // 없는 경우 -> Nickname 객체 생성, map & 배열에 넣어주기
                    else {
                        Nickname n = new Nickname(nickname);
                        map.put(id, n);
                        who.add(n);
                    }
                    break;
                case "Leave":
                    inOrOut.add(" 나갔습니다.");
                    who.add(map.get(id));
                    break;
                case "Change":
                    nickname = st.nextToken();
                    map.get(id).change(nickname);
                    break;
            }

        }
        ArrayList<String> result = new ArrayList<>();
        for (
                int i = 0; i < who.size(); i++) {
            result.add(who.get(i).nickname + "님이" + inOrOut.get(i));
        }

        String[] answer = new String[result.size()];
        for (
                int i = 0;
                i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}


class Nickname {
    String nickname;

    public Nickname(String nickname) {
        this.nickname = nickname;
    }

    // setter 의 역할
    public void change(String newNickname) {
        nickname = newNickname;
    }
}

