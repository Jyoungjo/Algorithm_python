import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth: clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 1) + 1);
        }
        
        for (Integer v: map.values()) {
            answer *= v;
        }
        
        return answer - 1;
    }
}