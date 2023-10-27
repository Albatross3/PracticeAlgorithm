package Programmers.CodingTestKit.깊이_너비우선탐색.여행경로;

import java.util.*;
public class Solution {
    static int N;
    static boolean[] isVisited;
    static List<String> routes;
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        isVisited = new boolean[N];

        routes = new ArrayList<>();
        getRoute("ICN", tickets ,"ICN", 0);
        Collections.sort(routes);

        return routes.get(0).split(" ");
    }

    public void getRoute(String start, String[][] tickets, String path, int count) {
        if(count == N) {
            routes.add(path);
            return;
        }

        for(int i=0; i<tickets.length; i++) {
            if(!isVisited[i] && tickets[i][0].equals(start)) {
                isVisited[i] = true;
                getRoute(tickets[i][1], tickets, path+" "+tickets[i][1], count+1);
                isVisited[i] = false;
            }
        }
    }
}
