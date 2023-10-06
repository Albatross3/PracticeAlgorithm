package SAMSUNG.SW_2023_상반기.오전.포탑_부수기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static Turret[][] map;
    static List<Turret> impactedTurret;
    static int[] dx = {0, 1, 0, -1}; // 우/하/좌/상
    static int[] dy = {1, 0, -1, 0}; // 우/하/좌/상
    static int[] lx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] ly = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Turret[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = new Turret(Integer.parseInt(st.nextToken()), 0, i, j);
            }
        }
        for (int time = 1; time <= K; time++) {
            // 부서지지 않은 터렛 1개인지 확인
            if (findNotCollapsedTurret() == 1) {
                break;
            }

            // 1. 공격자 선정 및 공격력 증가 및 공격 시점 증가
            Turret attacker = findAttacker();
            attacker.attack += N + M;
            attacker.t = time;
            // 2. 공격자의 공격
            //  2-1. 공격 대상자 선정
            Turret target = findTarget(attacker);
            //  2-2. 레이저 공격 후 포탄 공격 -> (최단거리 or 8방향) + target 공격
            // 공격 받은 터렛들 -> 최단 경로 or 8방향 + defender
            impactedTurret = new ArrayList<>();
            Cor start = new Cor(attacker.x, attacker.y);
            Cor destination = new Cor(target.x, target.y);
            if (!laserAttack(start, destination, attacker.attack)) {
                bulletAttack(start, destination, attacker.attack);
            }
            impactedTurret.add(attacker);
            // 3. 포탑 부서짐

            // 4. 포탑 정비
            setup();
        }

        // 최대 공격력 포탑 찾기
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, map[i][j].attack);
            }
        }
        System.out.println(max);
    }

    public static int findNotCollapsedTurret() {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].attack != 0) {
                    num++;
                }
            }
        }
        return num;
    }

    private static Turret findAttacker() {
        PriorityQueue<Turret> weakTurretPQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.attack == o2.attack) {
                if (o1.t == o2.t) {
                    if (o1.x + o1.y == o2.x + o2.y) {
                        return o2.y - o1.y;
                    } else return (o2.x + o2.y) - (o1.x + o1.y);
                } else return o2.t - o1.t;
            } else return o1.attack - o2.attack;
        });
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].attack != 0) {
                    weakTurretPQ.add(map[i][j]);
                }
            }
        }
        return weakTurretPQ.poll();
    }

    private static Turret findTarget(Turret attacker) {
        PriorityQueue<Turret> strongTurretPQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.attack == o2.attack) {
                if (o1.t == o2.t) {
                    if (o1.x + o1.y == o2.x + o2.y) {
                        return o1.y - o2.y;
                    } else return (o1.x + o1.y) - (o2.x + o2.y);
                } else return o1.t - o2.t;
            } else return o2.attack - o1.attack;
        });
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(i == attacker.x && j == attacker.y) continue;
                if (map[i][j].attack != 0) {
                    strongTurretPQ.add(map[i][j]);
                }
            }
        }
        return strongTurretPQ.poll();
    }


    private static boolean laserAttack(Cor start, Cor target, int attack) {
        Queue<Cor> q = new LinkedList<>();
        q.add(start);
        boolean[][] isVisited = new boolean[N][M];
        isVisited[start.x][start.y] = true;
        Cor[][] come = new Cor[N][M];

        boolean isFindShortestPath = false;
        while (!q.isEmpty()) {
            Cor c = q.poll();
            if (c.x == target.x && c.y == target.y) {
                isFindShortestPath = true;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = (c.x + dx[d] + N) % N;
                int ny = (c.y + dy[d] + M) % M;
                if (!isVisited[nx][ny] && map[nx][ny].attack != 0) {
                    isVisited[nx][ny] = true;
                    come[nx][ny] = c;
                    q.add(new Cor(nx, ny));
                }
            }
        }

        if (!isFindShortestPath) {
            return false;
        }

        // 역추적으로 경로 찾고 impactedTurret 에 넣기
        int x = target.x;
        int y = target.y;
        while (x != start.x || y != start.y) {
            impactedTurret.add(map[x][y]);
            // 한꺼번에 변하는 문제 x 가 뒤에 영향을 준다
            Cor cor = come[x][y];
            x = cor.x;
            y = cor.y;
        }
        // 공격
        attack(target.x, target.y, attack);
        return true;
    }

    private static void bulletAttack(Cor start, Cor target, int attack) {
        impactedTurret.add(map[target.x][target.y]);
        for (int l = 0; l < 8; l++) {
            int nx = (target.x + lx[l] + N) % N;
            int ny = (target.y + ly[l] + M) % M;
            if (nx == start.x && ny == start.y) continue;
            if (map[nx][ny].attack != 0) {
                impactedTurret.add(map[nx][ny]);
            }
        }
        // 공격
        attack(target.x, target.y, attack);
    }

    private static void attack(int targetX, int targetY, int attack) {
        // impactedTurret 에 있는 애들 공격
        for (Turret turret : impactedTurret) {
            if (turret.x == targetX && turret.y == targetY) {
                turret.attack = Math.max(0, turret.attack - attack);
            } else {
                turret.attack = Math.max(0, turret.attack - attack / 2);
            }
        }
    }

    public static void setup() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!impactedTurret.contains(map[i][j]) && map[i][j].attack != 0) {
                    map[i][j].attack += 1;
                }
            }
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

    static class Turret {
        int attack;
        int t;
        int x;
        int y;

        public Turret(int attack, int t, int x, int y) {
            this.attack = attack;
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }
}
