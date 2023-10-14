package SAMSUNG.SW_2023_상반기.오후.코드트리_채점기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int Q;
    static int N;

    static PriorityQueue<Integer> judge;
    static Map<String, PriorityQueue<Task>> waitingQueueByDomain;
    static Set<String> waitingQueueUrlSet;

    static Map<String, Integer> judgingDomainMap;
    static Map<String, int[]> domainJudgingHistory;

    static Map<Integer, Task> judging; //  채점기 : task
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            if(order == 100) {
                N = Integer.parseInt(st.nextToken());
                String u = st.nextToken();
                readyJudge(u);
            }

            else if(order == 200) {
                int t = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                String u = st.nextToken();
                requestJudge(t,p,u);
            }

            else if(order == 300) {
                int t = Integer.parseInt(st.nextToken());
                tryJudge(t);
            }

            else if(order == 400) {
                int t = Integer.parseInt(st.nextToken());
                int id = Integer.parseInt(st.nextToken());
                endJudge(t, id);
            }

            else if(order == 500) {
                int t = Integer.parseInt(st.nextToken());
                int size = printWaitingQueueSize();
                sb.append(size).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void readyJudge(String u) {
        judge = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            judge.add(i);
        }

        waitingQueueByDomain = new HashMap<>();
        PriorityQueue<Task> pq = new PriorityQueue<>();
        String domain = getDomain(u);
        Task initTask = new Task(1, 0, u, domain);
        pq.add(initTask);

        waitingQueueByDomain.put(domain, pq);

        // 대기 큐에 들어 있는 url set
        waitingQueueUrlSet = new HashSet<>();
        waitingQueueUrlSet.add(u);

        // 현재 진행중인 domain set -> domain : 개수
        judgingDomainMap = new HashMap<>();

        // 도메인의 가장 최근 채점 기록 -> domain : {start, end}
        domainJudgingHistory = new HashMap<>();

        // 현재 진행중인 task들 -> 채점기 : task
        judging = new HashMap<>();
    }

    static void requestJudge(int t, int p, String u) {
        if(!waitingQueueUrlSet.contains(u)) {
            String domain = getDomain(u);
            Task newTask = new Task(p, t, u, domain);

            // 해당 도메인 존재하는 경우 -> 그 그룹 pq 에 넣어주기
            if(waitingQueueByDomain.containsKey(domain)) {
                waitingQueueByDomain.get(domain).add(newTask);
            }
            // 없으면 pq 만들고 넣어주기
            else {
                PriorityQueue<Task> pq = new PriorityQueue<>();
                pq.add(newTask);
                waitingQueueByDomain.put(domain, pq);
            }

            waitingQueueUrlSet.add(u);
        }
    }

    static void tryJudge(int t) {
        // 채점기 가능여부 판단
        if(judge.isEmpty()) return;

        PriorityQueue<Task> tempPQ = new PriorityQueue<>();
        for(String domain : waitingQueueByDomain.keySet()) {
            if(waitingQueueByDomain.get(domain).isEmpty()) continue;
            if(firstCondition(domain) && secondCondition(domain, t)) {
                tempPQ.add(waitingQueueByDomain.get(domain).poll());
            }
        }

        if(tempPQ.isEmpty()) return;
        Task selectedTask = tempPQ.poll();

        // url Set 에서 제거
        waitingQueueUrlSet.remove(selectedTask.u);
        selectedTask.start = t;

        // 채점기 선택
        int selectedJudge = judge.poll();
        judging.put(selectedJudge, selectedTask);

        // 채점 진행중 domain set 에 추가
        judgingDomainMap.put(selectedTask.domain, judgingDomainMap.getOrDefault(selectedTask.domain, 0)+1);

        // 원래 그룹별 pq 로 복귀
        while(!tempPQ.isEmpty()) {
            Task task = tempPQ.poll();
            waitingQueueByDomain.get(task.domain).add(task);
        }
    }

    private static String getDomain(String url) {
        String[] split = url.split("/");
        return split[0];
    }

    private static boolean firstCondition(String domain) {
        // 현재 채점 진행 중인 도메인인지 확인
        return !judgingDomainMap.containsKey(domain);
    }

    private static boolean secondCondition(String domain, int t) {
        // 도메인의 최근 채점 시작 시각과 종료시각에 따른 확인
        // 도메인 없는 경우
        if(!domainJudgingHistory.containsKey(domain)) return true;

        int[] recentHistory = domainJudgingHistory.get(domain);
        int start = recentHistory[0];
        int gap = recentHistory[1] - start;

        if(t < start + 3*gap) {
            return false;
        }

        return true;
    }

    static void endJudge(int t, int id) {
        // 채점 진행 map 개수 줄이기
        // 채점 기록 history 관리 필요
        // 채점기 원래대로 복구시켜야 함
        if(judging.containsKey(id)) {
            Task task = judging.get(id);
            String domain = task.domain;

            if(judgingDomainMap.get(domain)==1) {
                judgingDomainMap.remove(domain);
            } else {
                judgingDomainMap.put(domain, judgingDomainMap.get(domain)-1);
            }

            // history 가 있는 경우
            task.end = t;
            if(domainJudgingHistory.containsKey(domain)) {
                domainJudgingHistory.get(domain)[0] = task.start;
                domainJudgingHistory.get(domain)[1] = task.end;
            } else {
                int[] newHistory = new int[] {task.start, task.end};
                domainJudgingHistory.put(domain, newHistory);
            }

            judge.add(id);

            judging.remove(id);
        }
    }

    static int printWaitingQueueSize() {
        int sum = 0;
        for(String domain : waitingQueueByDomain.keySet()) {
            sum += waitingQueueByDomain.get(domain).size();
        }
        return sum;
    }

    static class Task implements Comparable<Task> {
        int p; // 우선순위
        int t; // 들어온 시각
        String u; // url
        String domain;
        int start; // 채점 시작 시각
        int end; // 채점 종료 시각

        public Task(int p, int t, String u, String domain) {
            this.p = p;
            this.t = t;
            this.u = u;
            this.domain = domain;
            this.start = 0;
            this.end = 0;
        }

        @Override
        public int compareTo(Task o) {
            if(this.p == o.p) {
                return this.t - o.t;
            }
            else return this.p - o.p;
        }
    }
}
