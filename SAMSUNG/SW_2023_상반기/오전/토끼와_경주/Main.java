package SAMSUNG.SW_2023_상반기.오전.토끼와_경주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int Q;
    static int N, M, P;
    static Map<Integer, Rabbit> mapping;
    static PriorityQueue<Rabbit> pq;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static int[] indexX;
    static int[] indexY;
    static Map<Integer, Long> excludeScore;
    static Map<Integer, Long> realScore;
    static long bestScore;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(Q-->0) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            if(order==100) {
                ready(st);
            }

            else if(order==200) {
                int k = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                race(k, s);
            }

            else if(order==300) {
                int id = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                changeMovingDistance(id, l);
            }

            else if(order==400) {
                bestScore = selectBestScoreRabbit();
            }
        }

        System.out.println(bestScore);
    }

    static void ready(StringTokenizer st) {
        mapping = new LinkedHashMap<>();
        excludeScore = new HashMap<>();
        realScore = new HashMap<>();
        pq = new PriorityQueue<>((o1,o2) -> {
            if(o1.count == o2.count) {
                if(o1.x + o1.y == o2.x + o2.y) {
                    if(o1.x == o2.x) {
                        if(o1.y == o2.y) {
                            return o1.id - o2.id;
                        } else return o1.y - o2.y;
                    } else return o1.x - o2.x;

                } else return (o1.x + o1.y) - (o2.x + o2.y);
            } else return o1.count - o2.count;
        });
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        for(int i=0; i<P; i++) {
            int id = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Rabbit rabbit =  new Rabbit(id, d);
            mapping.put(id, rabbit);
            pq.add(rabbit);
            excludeScore.put(id, 0L);
            realScore.put(id, 0L);
        }
        indexX = new int[N+N-2];
        indexY = new int[M+M-2];

        for(int i=0; i<indexX.length; i++) {
            if(i < N) indexX[i] = i;
            else indexX[i] = indexX.length - i;
        }

        for(int i=0; i<indexY.length; i++) {
            if(i < M) indexY[i] = i;
            else indexY[i] = indexY.length - i;
        }
    }

    static void race(int k, int s) {
        Set<Integer> selectedIdSet = new HashSet<>();
        while(k-->0) {
            Rabbit selectedRabbit = pq.poll();
            selectedIdSet.add(selectedRabbit.id);
            selectedRabbit.count++;
            moveRabbit(selectedRabbit);
            pq.add(selectedRabbit);
        }

        // 한 번이라도 뽑힌 적이 있는 토끼 중 우선 순위가 가장 높은 토끼에 s 더하기
        PriorityQueue<Rabbit> scorePQ = new PriorityQueue<>((o1,o2) -> {
            if(o1.x + o1.y == o2.x + o2.y) {
                if(o1.x == o2.x) {
                    if(o1.y == o2.y) {
                        return o2.id - o1.id;
                    } else return o2.y - o1.y;
                } else return o2.x - o1.x;
            } else return (o2.x + o2.y) - (o1.x + o1.y);
        });
        for(int id : selectedIdSet) {
            scorePQ.add(mapping.get(id));
        }
        int lastSelectedId = scorePQ.poll().id;
        realScore.put(lastSelectedId, realScore.get(lastSelectedId)+s);
    }

    private static void moveRabbit(Rabbit rabbit) {
        List<int[]> positions = new ArrayList<>();
        for(int dir=0; dir<4; dir++) {
            int nextXIndex = Math.abs(rabbit.x + rabbit.d*dx[dir]) % (2*N-2);
            int nextYIndex = Math.abs(rabbit.y + rabbit.d*dy[dir]) % (2*M-2);
            int x = indexX[nextXIndex];
            int y = indexY[nextYIndex];
            positions.add(new int[] {x,y});
        }

        positions.sort((o1, o2) -> {
            if(o1[0] + o1[1] == o2[0] + o2[1]) {
                if(o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else return o2[0] - o1[0];
            } else return (o2[0]+o2[1]) - (o1[0]+o1[1]);
        });

        int nextX = positions.get(0)[0];
        int nextY = positions.get(0)[1];

        rabbit.x = nextX;
        rabbit.y = nextY;

        int getScore = nextX + nextY + 2;

        int selectedId = rabbit.id;
        excludeScore.put(selectedId, excludeScore.get(selectedId) + getScore);
    }

    static void changeMovingDistance(int id, int l) {
        mapping.get(id).d *= l;
    }

    static long selectBestScoreRabbit() {
        for(int excludeId : excludeScore.keySet()) {
            long plusScore = excludeScore.get(excludeId);
            for(int id : realScore.keySet()) {
                if(id != excludeId) {
                    realScore.put(id, realScore.get(id) + plusScore);
                }
            }
        }

        long maxScore = 0;
        for(int id : realScore.keySet()) {
            maxScore = Math.max(maxScore, realScore.get(id));
        }
        return maxScore;
    }

    static class Rabbit {
        int id;
        int d; // 이동 거리
        int x;
        int y;
        int count; // 뛴 횟수

        public Rabbit(int id, int d) {
            super();
            this.id = id;
            this.d = d;
            this.x = 0;
            this.y = 0;
            this.count = 0;
        }
    }
}
