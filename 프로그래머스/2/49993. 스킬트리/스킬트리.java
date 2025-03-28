import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 1; i <= skill.length(); i++) {
            map.put(skill.charAt(i - 1), i);
        }
        
        for (int i = 0; i < skill_trees.length; i++) {
            int rank = 1;
            boolean isPossible = true;
            String st = skill_trees[i];
            for (int j = 0; j < st.length(); j++) {
                if (map.get(st.charAt(j)) == null) continue;
                
                if (map.get(st.charAt(j)) > rank++) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) answer++;
        }
        return answer;
    }
}