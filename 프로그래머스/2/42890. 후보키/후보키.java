import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet<>();
    
    public int solution(String[][] relation) {
        for (int i = 0; i < relation[0].length; i++) {
            combinations(relation, 0, 0, 0, i + 1);
        }
        
        return visited.size();
    }
    
    private void combinations(String[][] relation, int start, int depth, int mask, int limit) {
        if (depth == limit) {
            if (isMinimal(mask) && isUnique(relation, mask)) visited.add(mask);
            return;
        }
        
        for (int i = start; i < relation[0].length; i++) {
            combinations(relation, i + 1, depth + 1, mask | (1 << i), limit);
        }
    }
    
    private boolean isMinimal(int mask) {
        for (int v : visited) {
            if ((mask & v) == v) return false;
        }
        
        return true;
    }
    
    private boolean isUnique(String[][] relation, int mask) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < relation[0].length; j++) {
                if ((mask & (1 << j)) != 0) sb.append(relation[i][j]).append(",");
            }
            
            if (!set.add(sb.toString())) return false;
        }
        
        return true;
    }
}