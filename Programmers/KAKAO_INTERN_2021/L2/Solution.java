package Programmers.KAKAO_INTERN_2021.L2;

public class Solution {
    // 북동남서
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    // 북동,동남,남서,서북,
    static int[] ty={-1,1,1,-1};
    static int[] tx={1,1,-1,-1};


    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int x=0; x<5; x++){
            String[] room=places[x];
            // 각 대기실
            char[][] tempRoom=new char[5][5];
            for(int i=0; i<5; i++){
                String row=room[i];
                for(int j=0; j<5; j++){
                    tempRoom[i][j]=row.charAt(j);
                }
            }
            // 각 대기실에서 거리두기 여부 판단
            boolean keepDistance=true;
            loop:
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(tempRoom[i][j]=='P'){
                        keepDistance=isKeepDist(tempRoom,i,j);
                        if(!keepDistance) break loop;
                    }
                }
            }
            if(keepDistance) answer[x]=1;
            else answer[x]=0;
        }

        return answer;
    }
    public static boolean isKeepDist(char[][] room,int i,int j){
        boolean keepDistance=true;
        int ni,nj;
        // 북동남서
        for(int d=0; d<4; d++){
            for(int s=1; s<=2; s++){
                ni=i+s*dy[d];
                nj=j+s*dx[d];
                if(ni>=0 && ni<5 && nj>=0 && nj<5){
                    if(room[ni][nj]=='X') break;
                    if(room[ni][nj]=='P'){
                        keepDistance=false;
                        break;
                    }
                }
            }
        }
        // 대각선
        for(int d=0; d<4; d++){
            ni=i+ty[d];
            nj=j+tx[d];
            if(ni>=0 && ni<5 && nj>=0 && nj<5){
                if(room[ni][nj]=='X') continue;
                if(room[ni][nj]=='P'){
                    if(room[ni][j] !='X' || room[i][nj]!='X') {
                        keepDistance=false;
                        break;
                    }
                }
            }
        }
        return keepDistance;
    }
}
