import java.util.*;

class Solution {
    Map<String, Integer> cache = new HashMap<>();
    
    public int solution(int cacheSize, String[] cities) {
        /*
            1. 캐시 미스
                1-1. 캐시 사이즈가 가득 참
                    제일 오래 있던 캐시 지우고 현재 내용을 캐싱
                1-2. 캐시 사이즈 여유 있음
                    그대로 캐싱
                시간 +5
            2. 캐시 히트
                기존 캐시 인덱스 변화
                시간 +1
        */
        int answer = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if (cache.get(city) == null) {
                if (cache.size() == cacheSize) LRU();
                if (cacheSize > 0) cache.put(city, i);
                answer += 5;
            } else {
                cache.put(city, i);
                answer++;
            }
        }
        return answer;
    }
    
    private void LRU() {
        int idx = 100001;
        String result = "";
        
        for (String key : cache.keySet()) {
            int i = cache.get(key);
            if (i < idx) {
                idx = i;
                result = key;
            }
        }
        
        cache.remove(result);
    }
}