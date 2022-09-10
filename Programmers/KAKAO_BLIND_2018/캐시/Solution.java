package Programmers.KAKAO_BLIND_2018.캐시;

import java.util.*;

// ~10:30
// 10:08 알고리즘 설계 완료
// 10:37 1차 풀이 완료
// 10:40 일부 TC 통과 못함
// 11:00 문제 확인
public class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize==0) return 5*cities.length;
        int time = 0;
        // 모두 소문자로 변환
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }
        Map<String, Integer> m = new LinkedHashMap<>();
        int num = 1;
        for (int i = 0; i < cities.length; i++) {
            if(!m.containsKey(cities[i]))
                m.put(cities[i], num++);
        }

        City[] arr = new City[100000 + 1];
        LinkedList<City> cache = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {
            int index = m.get(cities[i]);
            if(arr[index]==null){
                arr[index] = new City(cities[i], 0,  false);
            }
            // 1. cache 있는 경우
            if (arr[index].isIn) {
                arr[index].timeStamp=i;
                time += 1;
            }
            // 2. cache 없는 경우
            else {
                // 1. full 일 때 -> 정렬 후 삭제
                if (cache.size() == cacheSize) {
                    Collections.sort(cache);
                    City removed=cache.poll();
                    removed.timeStamp = 0;
                    removed.isIn=false;
                }
                arr[index].timeStamp = i;
                arr[index].isIn = true;
                cache.add(arr[index]);
                time += 5;
            }
        }
        return time;
    }
}
class City implements Comparable<City>{
    String cityName;
    int timeStamp;
    boolean isIn;

    public City(String cityName, int timeStamp, boolean isIn) {
        this.cityName = cityName;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override
    public int compareTo(City o) {
        return this.timeStamp - o.timeStamp;
    }
}
