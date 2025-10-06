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
    List<Food> foodList = new LinkedList<>();
    
    public int solution(int[] food_times, long k) {
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        
        Collections.sort(foodList);
        
        int foodCnt = foodList.size(), idx = 0, prevTime = 0;
        for (Food food : foodList) {
            long diff = food.time - prevTime;
            if (diff > 0) {
                long spend = diff * foodCnt;
                if (k - spend >= 0) {
                    k -= spend;
                    prevTime = food.time;
                } else {
                    k %= foodCnt;
                    foodList.subList(idx, foodList.size()).sort(Comparator.comparingInt(o -> o.order));
                    return foodList.get(idx + (int) k).order;
                }
            }
            
            idx++;
            foodCnt--;
        }
        
        return -1;
    }
}