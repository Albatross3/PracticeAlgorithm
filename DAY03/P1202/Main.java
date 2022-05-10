//package DAY03.P1202;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
//public class Main {
//    public static void main(String[] args) {
//
//
//        // 가방 오름 차순 정렬
//        Arrays.sort(bags);
//        // 보석 무게 오름차순 정렬
//        Arrays.sort(jewelries, Comparator.comparingInt(Jewelry::getWeight));
//        // 보석 가격이 높은값 기준 합
//        PriorityQueue<Jewely> pq=new PriorityQueue<>(Comparator.comparingInt(Jewelry::getValue).reversed());
//
//        int jIndex=0;
//        long result =0;
//        // 1. 남은 가방 중 제일 작은 가방을 선택 <- 정렬
//        for (int i=0; i<bags.length; i++){
//            //2. 선택한 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 <-힙을 사용
//            while(jIndex < N && jewelries[jIndex].weight <=bag){
//                pq.add(jewelries[jIndex++]);
//            }
//            if(!pq.isEmpty()){
//                result += pq.poll().value;
//            }
//        }
//
//        System.out.println(result);
//    }
//}
//class Jewelry {
//    int weight;
//    int value;
//
//    public int getValue() {
//        return value;
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//    public Jewelry(int weight,int value){
//        this.weight=weight;
//        this.value=value;
//    }
//
//    @Override
//    public String toString() {
//        return "Jewelry{" +
//                "weight=" + weight +
//                ", value=" + value +
//                '}';
//    }
//}