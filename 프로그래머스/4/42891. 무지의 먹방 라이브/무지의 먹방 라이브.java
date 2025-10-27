import java.util.*;

class Food implements Comparable<Food> {
    int order, time;
    
    Food(int order, int time) {
        this.order = order;
        this.time = time;
    }
    
    @Override
    public int compareTo(Food o) {
        return this.time - o.time;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        /*
            1. k와 food_times.length 비교
            (1) 만약 k >= food_times.length 라면 한 사이클을 돌고 k -= len 하고 반복
            (2) k < food_times.length 라면 해당 턴까지 먹고 그 다음 음식 idx 리턴
        */
        
        List<Food> foodList = new LinkedList<>();
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        
        Collections.sort(foodList);
        
        int tot = foodList.size(), idx = 0, prev = 0;
        for (Food food : foodList) {
            long diff = food.time - prev;
            if (diff > 0) {
                long spend = diff * tot;
                if (k - spend >= 0) {
                    k -= spend;
                    prev = food.time;
                } else {
                    k %= tot;
                    foodList.subList(idx, foodList.size()).sort(Comparator.comparingInt(o -> o.order));
                    return foodList.get(idx + (int) k).order;
                }
            }
            
            idx++;
            tot--;
        }
        
        return -1;
    }
}