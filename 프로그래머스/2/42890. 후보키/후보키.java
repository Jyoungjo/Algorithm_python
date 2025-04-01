import java.util.*;

class Solution {
    Set<Integer> comb = new HashSet<>();
    
    public int solution(String[][] relation) {
        for (int i = 0; i < relation[0].length; i++) {
            combinations(relation, 0, 0, i + 1);
        }

        return comb.size();
    }
    
    private void combinations(String[][] relation, int mask, int depth, int limit) {
        if (depth == limit) {
            if (isMinimal(mask) && isUnique(relation, mask)) comb.add(mask);
            return;
        }
        
        for (int i = 0; i < relation[0].length; i++) {
            if ((mask & (1 << i)) != 0) continue;
            combinations(relation, mask | (1 << i), depth + 1, limit);
        }
    }
    
    private boolean isMinimal(int mask) {
        for (int n : comb) {
            if ((n & mask) == n) return false;
        }
        return true;
    }
    
    private boolean isUnique(String[][] relation, int mask) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            String tmp = "";
            for (int j = 0; j < relation[0].length; j++) {
                if ((mask & (1 << j)) != 0) tmp += relation[i][j];
            }
            
            if (set.contains(tmp)) return false;
            else set.add(tmp);
        }
        
        return true;
    }
}