package SAMSUNG.SW_2022_하반기.오전.산타의_선물_공장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int q;
    static int n,m;
    static Map<Integer, Box> map = new HashMap<>();
    static Box[] frontDummyList;
    static Box[] backDummyList;
    static boolean[] isBrokenBelt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        q = Integer.parseInt(br.readLine());
        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if(order == 100) {
                makeFactory(st);
            }
            else if(order == 200) {
                int w_max = Integer.parseInt(st.nextToken());
                sb.append(getOutBox(w_max)).append("\n");
            } else if(order == 300) {
                int r_id = Integer.parseInt(st.nextToken());
                sb.append(removeBox(r_id)).append("\n");
            } else if(order == 400) {
                int f_id = Integer.parseInt(st.nextToken());
                sb.append(identifyBox(f_id)).append("\n");
            } else if(order == 500) {
                int b_num = Integer.parseInt(st.nextToken());
                sb.append(brokeBelt(b_num)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void makeFactory(StringTokenizer st) {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        frontDummyList = new Box[m+1];
        backDummyList = new Box[m+1];
        for(int i=1; i<=m; i++) {
            frontDummyList[i] = new Box(-1,-1,-1);
        }
        for(int i=1; i<=m; i++) {
            backDummyList[i] = new Box(-1,-1,-1);
        }

        isBrokenBelt = new boolean[m+1];
        int[] id = new int[n];
        int[] weight = new int[n];

        for(int i=0; i<n; i++) {
            id[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for(int b=0; b<m; b++) {
            Box cur = frontDummyList[b+1];
            for(int index=0; index<n/m; index++) {
                Box box = new Box(id[b*n/m + index], weight[b*n/m + index], b+1);
                cur.back = box;
                box.front = cur;
                cur = box;
                if(index == n/m-1) {
                    cur.back = backDummyList[b+1];
                    backDummyList[b+1].front = cur;
                }
                map.put(id[b*n/m + index], box);
            }
        }
    }

    static int getOutBox(int maxWeight) {
        int sumWeight = 0;
        for(int i=1; i<=m; i++) {
            // 아무것도 없는 경우
            if(frontDummyList[i].back.id == -1) continue;

            Box outBox = frontDummyList[i].back;
            if(outBox.weight <= maxWeight) {
                sumWeight += outBox.weight;
                // 먼저 연결 후 제거
                outBox.front.back = outBox.back;
                outBox.back.front = outBox.front;
                outBox.back = null;
                outBox.front = null;
            } else {
                // 1개가 아닌 경우에 대해서만 처리
                if(outBox.back.id!=-1) {
                    outBox.front.back = outBox.back;
                    outBox.back.front = outBox.front;
                    backDummyList[i].front.back = outBox;
                    outBox.front = backDummyList[i].front;
                    backDummyList[i].front = outBox;
                    outBox.back = backDummyList[i];
                }
            }
        }
        return sumWeight;
    }

    static int removeBox(int id) {
        Box tryToRemoveBox = map.get(id);
        if(tryToRemoveBox == null) return -1;
        // id 값이 있어도 하차되거나 제거된 경우
        if(tryToRemoveBox.front==null && tryToRemoveBox.back==null) return -1;

        // 제거
        tryToRemoveBox.front.back = tryToRemoveBox.back;
        tryToRemoveBox.back.front = tryToRemoveBox.front;
        tryToRemoveBox.front = null;
        tryToRemoveBox.back = null;
        return id;
    }

    static int identifyBox(int id) {
        Box tryToFindBox = map.get(id);
        if(tryToFindBox == null) return -1;
        // id 값이 있어도 하차되거나 제거된 경우
        if(tryToFindBox.front==null && tryToFindBox.back==null) return -1;

        // 해당 상자 포함 위를 전부 앞으로 가져오기
        // 맨 앞의 상자가 아니라면
        int numBelt = tryToFindBox.numBelt;
        if(tryToFindBox.front.id!=-1) {
            Box wasRear = frontDummyList[numBelt].back;
            Box toBeRear = tryToFindBox.front;
            Box toBeMiddle = backDummyList[numBelt].front;
            // 찾는 놈 맨 앞으로
            frontDummyList[numBelt].back = tryToFindBox;
            tryToFindBox.front = frontDummyList[numBelt];
            // 찾는 놈의 앞 놈 맨 뒤로
            toBeRear.back = backDummyList[numBelt];
            backDummyList[numBelt].front = toBeRear;
            // 중간 놈 연결
            toBeMiddle.back = wasRear;
            wasRear.front = toBeMiddle;
        }
        return numBelt;
    }

    static int brokeBelt(int numBelt) {
        if(isBrokenBelt[numBelt]) return -1;
        int numBrokenBelt = numBelt;
        isBrokenBelt[numBelt] = true;
        while(isBrokenBelt[numBelt]) {
            numBelt = (numBelt+1)%m==0 ? m : (numBelt+1)%m;
        }
        // 망가진 벨트에서 옮길 상자가 있는 경우에 한해
        if(frontDummyList[numBrokenBelt].back.id!=-1) {
            // 옮겨질 상자들의 모든 numBelt 수정 필요
            Box cur = frontDummyList[numBrokenBelt].back;
            while(cur.id != -1) {
                cur.numBelt = numBelt;
                cur = cur.back;
            }
            // 상자 옮기기
            Box firstMoveBox = frontDummyList[numBrokenBelt].back;
            Box lastMoveBox = backDummyList[numBrokenBelt].front;

            frontDummyList[numBrokenBelt].back = backDummyList[numBrokenBelt];
            backDummyList[numBrokenBelt].front = frontDummyList[numBrokenBelt];

            Box lastBoxInUnbrokenBelt = frontDummyList[numBelt];
            while(lastBoxInUnbrokenBelt.back.id != -1) {
                lastBoxInUnbrokenBelt = lastBoxInUnbrokenBelt.back;
            }
            lastBoxInUnbrokenBelt.back = firstMoveBox;
            firstMoveBox.front = lastBoxInUnbrokenBelt;

            lastMoveBox.back = backDummyList[numBelt];
            backDummyList[numBelt].front = lastMoveBox;
        }
        return numBrokenBelt;
    }

    static class Box {
        int id;
        int weight;
        int numBelt;
        Box front;
        Box back;

        public Box(int id, int weight, int numBelt) {
            this.id = id;
            this.weight = weight;
            this.numBelt = numBelt;
            front = null;
            back = null;
        }
    }
}
