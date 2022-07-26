package Programmers.KAKAO_BLIND_2019.L4;

// 정확성, 효율성 모두를 고려한 코드
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompleteSolution {
    public static void main(String[] args) {
        CompleteSolution cs = new CompleteSolution();
        System.out.println(cs.solution(new int[]{3,1,2,3},8));
    }
    public int solution(int[] food_times, long k) {
        int n=food_times.length;
        List<Food> list = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            list.add(new Food(i + 1, food_times[i]));
        }
        list.sort(compAmount);
        int prevHeight=0;
        int i=0;
        for (Food f : list) {
            long heightDiff=f.amount-prevHeight;
            if(heightDiff!=0) {
                long spend=heightDiff*n;
                if (spend <= k) {
                    k-=spend;
                }else{
                    List<Food> subList=list.subList(i, food_times.length);
                    subList.sort(compOrder);
                    long index = k % n;
                    return subList.get((int) index).order;
                }
            }
            prevHeight=f.amount;
            --n;
            i++;
        }
        return -1;
    }

    Comparator<Food> compAmount = new Comparator<Food>() {
        @Override
        public int compare(Food o1, Food o2) {
            return o1.amount - o2.amount;
        }
    };

    Comparator<Food> compOrder = new Comparator<Food>() {
        @Override
        public int compare(Food o1, Food o2) {
            return o1.order - o2.order;
        }
    };
}

class Food  {
    int order;
    int amount;

    public Food(int order, int amount) {
        this.order = order;
        this.amount = amount;
    }
}


