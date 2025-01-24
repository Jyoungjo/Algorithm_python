import java.util.*;


class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        List<Integer> kList = new ArrayList<>(map.keySet());
        Collections.sort(kList, (o1, o2) -> map.get(o2) - map.get(o1));
        for (int key : kList) {
            // k = 3 -> 2 2 1 1 1
            int tot = map.get(key);
            if (k <= 0) break;
            k -= Math.min(tot, k);
            answer++;
        }
        
        return answer;
    }
}