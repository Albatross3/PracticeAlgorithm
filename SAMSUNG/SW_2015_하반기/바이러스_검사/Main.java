package SAMSUNG.SW_2015_하반기.바이러스_검사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수학 문제
public class Main {
    static int numOfRestaurant;
    static int[] perPeopleOfRestaurant;
    static int maxNumOfLeader, maxNumOfFollower;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        numOfRestaurant = Integer.parseInt(br.readLine());
        perPeopleOfRestaurant = new int[numOfRestaurant];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOfRestaurant; i++) {
            perPeopleOfRestaurant[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        maxNumOfLeader = Integer.parseInt(st.nextToken());
        maxNumOfFollower = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i = 0; i < numOfRestaurant; i++) {
            if (perPeopleOfRestaurant[i] > maxNumOfLeader) {
                perPeopleOfRestaurant[i] -= maxNumOfLeader; // 팀장 1 배치
                result += 1 + perPeopleOfRestaurant[i] / maxNumOfFollower;
                if (perPeopleOfRestaurant[i] % maxNumOfFollower != 0) result += 1;
            } else {
                result += 1;
            }
        }
        System.out.println(result);
    }
}
