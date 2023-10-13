package Baekjoon.시뮬레이션.P21611;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    static List<int[]> attacks;
    static Map<Integer, Integer> hashMap = new HashMap<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static List<Cor> corList; // 불변

    static int[] scores = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        attacks = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int p = Integer.parseInt(st.nextToken());
            attacks.add(new int[] {d,p});
        }

        hashMap.put(0,2);
        hashMap.put(1,1);
        hashMap.put(2,3);
        hashMap.put(3,0);
        makeCorList();
        for(int r=0; r<m; r++) {
            int[] attack = attacks.get(r);
            // 1. 플레이어의 몬스터 공격
            monsterAttack(attack);

            // 2. 비어있는 위치 당겨주기
            monsterMove();

            // 3. 4번 이상 나오는 몬스터 삭제
            while(monsterFindAndRemove()) {
                // 비어있는 위치 당겨주기
                monsterMove();
            }

            // 4. 몬스터 재편성
            renewMonster();
        }
        System.out.println(scores[1] + 2*scores[2] + 3*scores[3]);
    }

    static void makeCorList() {
        int timing = 0;
        Set<Integer> changeTiming = new LinkedHashSet<>();
        for(int i=1; i<n; i++) {
            for(int j=0; j<2; j++) {
                timing += i;
                changeTiming.add(timing);
            }
        }

        corList = new ArrayList<>();
        int x = n/2;
        int y = n/2;
        int index = 0;
        int d = hashMap.get(index);
        for(int i=1; i<=n*n-1; i++) {
            x = x + dx[d];
            y = y + dy[d];
            corList.add(new Cor(x, y));
            if(changeTiming.contains(i)) {
                index = (index+1+4)%4;
                d = hashMap.get(index);
            }
        }
    }

    static void monsterAttack(int[] attack) {
        int d = attack[0];
        int p = attack[1];

        for(int l=1; l<=p; l++) {
            int nx = n/2 + l*dx[d];
            int ny = n/2 + l*dy[d];
            map[nx][ny] = 0;
        }
    }

    static void monsterMove() {
        // 최대 36000 정도의 연산
        for(int i=0; i<corList.size(); i++) {
            Cor c = corList.get(i);
            // 빈칸인 경우
            // 현재 빈칸 이후로 모두 빈칸인 경우 -> break 처리하면 더 빨라짐
            if(map[c.x][c.y] == 0) {
                for(int j=i+1; j<corList.size(); j++) {
                    Cor nc = corList.get(j);
                    if(map[nc.x][nc.y]!=0) {
                        map[c.x][c.y] = map[nc.x][nc.y];
                        map[nc.x][nc.y] = 0;
                        break;
                    }
                }
            }
        }
    }

    static boolean monsterFindAndRemove() {
        List<Integer> removeIndex = new ArrayList<>();

        int[] monsterList = new int[n*n]; // 마지막은 0 더미 처리
        for(int i=0; i<corList.size(); i++) {
            Cor c = corList.get(i);
            monsterList[i] = map[c.x][c.y];
        }

        // 마지막의 경우 어떻게 할지 -> 예외처리
        for(int i=0; i<monsterList.length-1; i++) {
            int m = monsterList[i];
            if(m==0) break;
            int count = 0;
            int lastIndex = i;
            for(int j=i; j<monsterList.length; j++) {
                if(monsterList[j]==m) {
                    count++;
                } else {
                    lastIndex = j-1;
                    break;
                }

            }
            if(count >= 4) {
                for(int index=i; index<=lastIndex; index++) {
                    removeIndex.add(index);
                }
            }
            i = lastIndex;
        }

        if(removeIndex.isEmpty()) return false;

        // 몬스터 제거
        for(int i : removeIndex) {
            Cor c = corList.get(i);
            scores[map[c.x][c.y]]++;
            map[c.x][c.y] = 0;
        }
        return true;
    }

    static void renewMonster() {
        int[] newMonsterList = new int[n*n-1];

        int[] monsterList = new int[n*n]; // 마지막은 0 더미 처리
        for(int i=0; i<corList.size(); i++) {
            Cor c = corList.get(i);
            if(map[c.x][c.y]!=0) {
                monsterList[i] = map[c.x][c.y];
            }
        }

        int index = 0;
        for(int i=0; i<monsterList.length-1; i++) {
            int m = monsterList[i];
            if(m==0) break;

            int count = 0;
            for(int j=i; j<monsterList.length-1; j++) {
                if(monsterList[j] == m) {
                    count++;
                } else {
                    i = j-1;
                    break;
                }
            }

            // 새로운 monster 배열에 넣어주기
            newMonsterList[index] = count;
            newMonsterList[index+1] = m;
            index+=2;
            if(index == n*n-1) break;
        }

        // 미로 속에 넣기
        for(int i=0; i<newMonsterList.length; i++) {
            Cor c = corList.get(i);
            map[c.x][c.y] = newMonsterList[i];
        }
    }

    static class Cor {
        int x;
        int y;

        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

