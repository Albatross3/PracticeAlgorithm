package SAMSUNG.SW_2022_상반기.오후.꼬리잡기놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[][] map;
    static int[][] indexTeam;
    static boolean[][] isVisited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static List<Team> teams;
    static int[] target;
    static int totalScore = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // indexTeam 만들기
        teams = new ArrayList<>();
        teams.add(new Team()); // dummy 팀 추가
        isVisited = new boolean[n][n];
        indexTeam = new int[n][n];
        int teamIndex=1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!isVisited[i][j] && map[i][j]!=0) {
                    Team team = new Team();
                    teams.add(team);
                    findTeam(new int[]{i,j}, teamIndex);
                    teamIndex++;
                }
            }
        }

        for(int round=1; round<=k; round++) {
            // 각 팀 머리 사람 따라서 한 칸 이동
            isVisited = new boolean[n][n];
            for(int i=1; i<teams.size(); i++) {
                move(teams.get(i).tail, 3, i);
            }

            // 공 던지기 -> 안 맞으면 -1 return
            int realRound = round%(4*n)==0 ? 4*n : round%(4*n);
            target = new int[2];
            int index = throwBall(realRound);

            // 팀인 경우에 사람 찾고 점수 계산
            if(index != -1) {
                isVisited = new boolean[n][n];
                int score = getScore(teams.get(index).head, 1, 1);
                teams.get(index).score += score*score;
                changeHeadAndTail(index);
            }
        }

        // 각 팀의 점수 합
        for(int i=1; i<teams.size(); i++) {
            totalScore += teams.get(i).score;
        }
        System.out.println(totalScore);
    }


    // 꼬리부터 시작해서 머리방향으로 이동
    // head tail 위치 변경
    static void move(int[] start, int order, int teamIndex) {
        int x = start[0];
        int y = start[1];
        isVisited[x][y] = true;
        if(order==1) {
            for(int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx<0 || nx>=n || ny<0 || ny>=n) continue;

                if(map[nx][ny]==4) {
                    teams.get(teamIndex).head[0] = nx;
                    teams.get(teamIndex).head[1] = ny;
                    map[nx][ny] = order;
                    break;
                }
            }
            return;
        }
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
            if(map[nx][ny]==0) continue;

            if(!isVisited[nx][ny] && map[nx][ny] <= order) {
                if(order==3) {
                    if(map[nx][ny]==1) continue;
                    map[x][y]=4;
                    teams.get(teamIndex).tail[0] = nx;
                    teams.get(teamIndex).tail[1] = ny;
                }
                int nextOrder = map[nx][ny];
                map[nx][ny] = order;
                move(new int[]{nx,ny}, nextOrder, teamIndex);
            }
        }
    }

    static int throwBall(int round) {
        int teamIndex = -1;
        if(round <= n) {
            for(int j=0; j<n; j++) {
                if(map[round-1][j]==1 || map[round-1][j]==2 || map[round-1][j]==3) {
                    teamIndex = indexTeam[round-1][j];
                    target[0] = round-1;
                    target[1] = j;
                    break;
                }
            }
        } else if(round <= 2*n) {
            round = round - n;
            for(int i=n-1; i>=0; i--) {
                if(map[i][round-1]==1 || map[i][round-1]==2 || map[i][round-1]==3) {
                    teamIndex = indexTeam[i][round-1];
                    target[0] = i;
                    target[1] = round-1;
                    break;
                }
            }
        } else if(round <= 3*n) {
            round = round - 2*n;
            for(int j=n-1; j>=0; j--) {
                if(map[n-round][j]==1 || map[n-round][j]==2 || map[n-round][j]==3) {
                    teamIndex = indexTeam[n-round][j];
                    target[0] = n-round;
                    target[1] = j;
                    break;
                }
            }
        } else if(round <= 4*n) {
            round = round - 3*n;
            for(int i=0; i<n; i++) {
                if(map[i][n-round]==1 || map[i][n-round]==2 || map[i][n-round]==3) {
                    teamIndex = indexTeam[i][n-round];
                    target[0] = i;
                    target[1] = n-round;
                    break;
                }
            }
        }

        return teamIndex;
    }

    static int getScore(int[] start, int length, int order) {
        int x = start[0];
        int y = start[1];
        isVisited[x][y] = true;
        if(x == target[0] && y == target[1]) {
            return length;
        }
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
            if(map[nx][ny]==4) continue;
            if(!isVisited[nx][ny] && map[x][y] <= map[nx][ny]) {
                if(order==1) {
                    if(map[nx][ny]==3) continue;
                }
                return getScore(new int[] {nx,ny}, length+1, map[nx][ny]);
            }
        }
        return -1;
    }

    static void changeHeadAndTail(int index) {
        Team t = teams.get(index);
        int prevTailX =  t.tail[0];
        int prevTailY =  t.tail[1];
        t.tail[0] = t.head[0];
        t.tail[1] = t.head[1];
        t.head[0] = prevTailX;
        t.head[1] = prevTailY;
        // map 도 바뀜
        map[t.head[0]][t.head[1]] = 1;
        map[t.tail[0]][t.tail[1]] = 3;
    }

    static void findTeam(int[] start, int teamIndex) {
        int x = start[0];
        int y = start[1];
        isVisited[x][y]=true;
        indexTeam[x][y]=teamIndex;
        if(map[x][y]==1) {
            teams.get(teamIndex).head[0]=x;
            teams.get(teamIndex).head[1]=y;
        } else if(map[x][y]==3) {
            teams.get(teamIndex).tail[0]=x;
            teams.get(teamIndex).tail[1]=y;
        }
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
            if(isVisited[nx][ny]) continue;
            if(map[nx][ny]!=0) {
                findTeam(new int[]{nx,ny}, teamIndex);
            }
        }
    }

    static class Team {
        int[] head;
        int[] tail;
        int score;

        public Team() {
            head = new int[2];
            tail = new int[2];
            score = 0;
        }
    }
}