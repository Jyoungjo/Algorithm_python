import java.util.*;

class Solution {
    Set<String> gemSet = new HashSet<>();
    
    public int[] solution(String[] gems) {
        int[] answer = new int[]{0, 0};
        gemSet.addAll(Arrays.asList(gems));

        Map<String, Integer> map = new HashMap<>();
        int l = 0, r = 0, len = 100001;
        while (r < gems.length) {
            map.put(gems[r], map.getOrDefault(gems[r], 0) + 1);
            Set<String> set = map.keySet();
            if (set.size() == gemSet.size()) {
                while (set.size() == gemSet.size()) {
                    map.put(gems[l], map.get(gems[l]) - 1);
                    if (map.get(gems[l]) == 0) {
                        set.remove(gems[l]);
                        map.remove(gems[l]);
                    }
                    l++;
                }

                if (len > r - --l + 1) {
                    len = r - l + 1;
                    answer[0] = l + 1;
                    answer[1] = r + 1;
                }
                l++;
            }
            r++;
        }

        return answer;
    }
}