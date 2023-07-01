package Programmers.KAKAO_BLIND_2023.표병합;

import java.util.*;
class Solution {
    static String[][] table;
    public String[] solution(String[] commands) {
        table = new String[50][50];
        List<String> list = new ArrayList<>();
        for(int i=0; i<commands.length; i++) {
            String[] split = commands[i].split(" ");
            String command = split[0];

            if(command.equals("UPDATE")) {
                // 특정 위치 update
                if(split.length == 4) {
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    String value = split[3];
                    table[r-1][c-1] = value;
                }
                // 같은 값들 update
                else if(split.length == 3) {
                    String value1 = split[1];
                    String value2 = split[2];
                    changeValue(value1, value2);
                }
            } else if(command.equals("MERGE")) {

            } else if(command.equals("UNMERGE")) {

            } else if(command.equals("PRINT")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);

                if(table[r-1][c-1] == null) list.add("EMPTY");
                else list.add(table[r-1][c-1]);
            }


        }

        String[] answer = new String[list.size()];
        int index = 0;
        for(String v : list) {
            answer[index++] = v;
        }
        return answer;
    }
    public static void changeValue(String value1, String value2) {
        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
                if(table[i][j].equals(value1)) {
                    table[i][j] = value2;
                }
            }
        }
    }
}
