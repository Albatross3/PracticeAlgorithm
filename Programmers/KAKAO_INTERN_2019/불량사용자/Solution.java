package Programmers.KAKAO_INTERN_2019.불량사용자;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public
class Solution {
    static List<List<String>> matchedBannedList = new ArrayList<>();
    static Set<Set<String>> bannedSetList = new HashSet<>();
    static int length;
    public int solution(String[] user_id, String[] banned_id) {
        length = banned_id.length;
        for(String id : banned_id) {
            matchedBannedList.add(getMatchedList(user_id, id));
        }

        getBannedSet(0, new HashSet<>());

        return bannedSetList.size();
    }

    static void getBannedSet(int depth , Set<String> tempSet) {
        if(depth == length) {
            bannedSetList.add(new HashSet<>(tempSet));
            return;
        }
        for(String id : matchedBannedList.get(depth)) {
            if(!tempSet.contains(id)) {
                tempSet.add(id);
                getBannedSet(depth+1 , tempSet);
                tempSet.remove(id);
            }
        }
    }

    static List<String> getMatchedList(String[] user_id, String regex) {
        regex = regex.replace('*', '.');
        List<String> list = new ArrayList<>();
        for(String id : user_id) {
            if(Pattern.matches(regex, id)) {
                list.add(id);
            }
        }
        return list;
    }
}
