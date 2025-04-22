import java.util.*;

class Solution {
    public int[][] solution(int n) {
        List<int[]> result = build(n, 1, 3);
        return result.toArray(int[][]::new);
    }
    
    private List<int[]> build(int n, int start, int end) {
        if (n == 1) return List.of(new int[]{start, end});
        
        int next = 6 - start - end;
        
        List<int[]> now = new ArrayList<>();
        now.addAll(build(n - 1, start, next));
        now.add(new int[]{start, end});
        now.addAll(build(n - 1, next, end));
        return now;
    }
}