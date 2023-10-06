package SAMSUNG.SW_2023_상반기.오전.포탑_부수기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static Turret[][] map;
    static int numOfTurret;
    static boolean[][] isImpacted;
    static List<Turret> attackedTurret;
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
                int attack = Integer.parseInt(st.nextToken());
                if (attack != 0) numOfTurret++;
                map[i][j] = new Turret(attack, 0, i, j);
            }
        }

        // 꺼내온 Turret 객체의 상태변화시키지 말것
        while (K-- > 0) {
            isImpacted = new boolean[N][M];
            // 1. 공격자 선정 및 공격력 증가 및 공격 시점 증가
            Turret attacker = findAttacker();
            attacker.attack += N + M;
            attacker.t++;
            int attack = attacker.attack;
            // 2. 공격자의 공격
            //  2-1. 공격 대상자 선정
            Turret defender = findDefender(attacker);

            isImpacted[attacker.x][attacker.y] = true;

            //  2-2. 레이저 공격 or 포탄 공격 -> 터렛 수 줄기 + 영향 받은 애들 추가해야함
            // 공격 받은 터렛들 -> 최단 경로 or 8방향 + defender
            attackedTurret = new ArrayList<>();
            Cor start = new Cor(attacker.x, attacker.y);
            Cor destination = new Cor(defender.x, defender.y);
            if (findShortestPath(start, destination)) {
                // 레이저 공격
                laserAttack(defender, attack);
            } else {
                // 포탄 공격
                bulletAttack(defender, attack);
            }

            // 3. 포탑 부서짐
            // 영향 받은 애들 상태 변경 시키기 -> isImpacted, numOfTurret
            collapseTurret();
            // 부서지지 않은 터렛 수 1개 되면 break
            if (numOfTurret == 1) {
                break;
            }
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

    private static Turret findAttacker() {
        PriorityQueue<Turret> weakTurretPQ = new PriorityQueue<>(new Comparator<Turret>() {
            @Override
            public int compare(Turret o1, Turret o2) {
                if (o1.attack == o2.attack) {
                    if (o1.t == o2.t) {
                        if (o1.x + o1.y == o2.x + o2.y) {
                            return (o2.x + o2.y) - (o1.x + o1.y);
                        } else return o2.y - o1.y;
                    } else return o2.t - o1.t;
                } else return o1.attack - o2.attack;
            }
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

    private static Turret findDefender(Turret attacker) {
        PriorityQueue<Turret> strongTurretPQ = new PriorityQueue<>(new Comparator<Turret>() {
            @Override
            public int compare(Turret o1, Turret o2) {
                if (o1.attack == o2.attack) {
                    if (o1.t == o2.t) {
                        if (o1.x + o1.y == o2.x + o2.y) {
                            return (o1.x + o1.y) - (o2.x + o2.y);
                        } else return o1.y - o2.y;
                    } else return o1.t - o2.t;
                } else return o2.attack - o1.attack;
            }
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

    private static boolean findShortestPath(Cor start, Cor destination) {
        Queue<Cor> q = new LinkedList<>();
        q.add(start);
        boolean[][] isVisited = new boolean[N][M];
        isVisited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Cor c = q.poll();
            if (c.x == destination.x && c.y == destination.y) {
                attackedTurret = c.path;
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int nx = getNX(c.x + dx[d]);
                int ny = getNY(c.y + dy[d]);
                if (map[nx][ny].attack != 0) {
                    isVisited[nx][ny] = true;
                    Cor next = new Cor(nx, ny, map[nx][ny]);
                    next.path.addAll(c.path);
                    q.add(next);
                }
            }
        }
        return false;
    }

    private static void laserAttack(Turret defender, int attack) {
        defender.attack -= attack;
        for (Turret turret : attackedTurret) {
            if (turret.x != defender.x || turret.y != defender.y) {
                map[turret.x][turret.y].attack -= attack / 2;
            }
        }
    }

    private static void bulletAttack(Turret defender, int attack) {
        defender.attack -= attack;
        attackedTurret.add(defender);
        int X = defender.x;
        int Y = defender.y;

        for (int l = 0; l < 8; l++) {
            int nx = getNX(X + lx[l]);
            int ny = getNY(Y + ly[l]);
            if (map[nx][ny].attack != 0) {
                map[nx][ny].attack -= attack / 2;
                if (map[nx][ny].attack <= 0) {
                    map[nx][ny].attack = 0;
                }
                attackedTurret.add(map[nx][ny]);
            }
        }
    }

    private static int getNX(int nx) {
        if(nx == -1) return N - 1;
        else if(nx == N) return 0;
        else return nx;
    }

    private static int getNY(int ny) {
        if(ny == -1) return M - 1;
        else if(ny == M) return 0;
        else return ny;
    }

    private static void collapseTurret() {
        for (Turret turret : attackedTurret) {
            if (map[turret.x][turret.y].attack == 0) {
                numOfTurret--;
            }
            isImpacted[turret.x][turret.y] = true;
        }
    }

    private static void setup() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isImpacted[i][j] && map[i][j].attack != 0) {
                    map[i][j].attack += 1;
                }
            }
        }
    }

    static class Cor {
        int x;
        int y;
        List<Turret> path;

        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
            path = new ArrayList<>();
        }

        public Cor(int x, int y, Turret turret) {
            this.x = x;
            this.y = y;
            path = new ArrayList<>();
            path.add(turret);
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
