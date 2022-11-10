package Programmers.KAKAO_BLIND_2019.무지의먹방라이브;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1,1,1},3));
    }
    public int solution(int[] food_times, long k) {
        // 음식 리스트 만들기 및 정렬
        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        Collections.sort(foodList);

        // 블록 단위로 제거
        int size = foodList.size();
        int height = 0;
        boolean isLeft=false;
        for(int i=0; i<size; i++) {
            long amount = (long)(foodList.get(0).foodAmount - height) * (foodList.size());
            if (k - amount < 0) {
                isLeft=true;
                break;
            }
            // update
            k -= amount;
            height = foodList.get(0).foodAmount;
            foodList = foodList.subList(1, foodList.size());
        }

        if(isLeft) {
            // 해당 블록에서 위치 구하기 -> %
            int location = (int) (k % foodList.size());
            foodList.sort((o1, o2) -> o1.foodNumber - o2.foodNumber);
            return foodList.get(location).foodNumber;
        }
        else return -1;
    }
}
class Food implements Comparable<Food>{
    int foodNumber;
    int foodAmount;
    public Food(int foodNumber, int foodAmount) {
        this.foodNumber = foodNumber;
        this.foodAmount = foodAmount;
    }

    @Override
    public int compareTo(Food o) {
        return this.foodAmount - o.foodAmount;
    }
}