import java.util.*;

class Info {
    int numsMask;
    int cNum;
    
    Info(int numsMask, int cNum) {
        this.numsMask = numsMask;
        this.cNum = cNum;
    }
}

class Solution {
    Set<Integer> result = new HashSet<>();
    int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        int len = q[0].length;
        Info[] infos = new Info[ans.length];
        for (int i = 0; i < ans.length; i++) {
            int mask = 0;
            for (int num : q[i]) {
                mask |= (1 << (num - 1));
            }
            infos[i] = new Info(mask, ans[i]);
        }
        
        // 가장 높은 cNum 기준으로 sort
        Arrays.sort(infos, (o1, o2) -> o2.cNum - o1.cNum);
        int limit = infos[0].cNum;
        
        Set<Integer> initialSets = new HashSet<>();
        for (Info info : infos) {
            if (info.cNum < limit) break;
            generateCombinations(initialSets, info.numsMask, info.cNum, 0, 0, n);
        }
        
        for (int setMask : initialSets) {
            dfs(setMask, infos, n, len);
        }
        
        return answer;
    }
    
    // dfs to fill up to len numbers
    private void dfs(int mask, Info[] infos, int n, int len) {
        if (Integer.bitCount(mask) == len) {
            boolean valid = true;
            for (Info info : infos) {
                int intersect = mask & info.numsMask;
                if (Integer.bitCount(intersect) != info.cNum) {
                    valid = false;
                    break;
                }
            }
            if (valid && !result.contains(mask)) {
                result.add(mask);
                answer++;
            }
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) continue; // already included
            dfs(mask | (1 << i), infos, n, len);
        }
    }
    
    // generate all combinations of cNum elements from numsMask
    private void generateCombinations(Set<Integer> result, int numsMask, int targetCount, int idx, int currentMask, int n) {
        if (Integer.bitCount(currentMask) == targetCount) {
            result.add(currentMask);
            return;
        }
        if (idx >= n) return;
        
        for (int i = idx; i < n; i++) {
            if ((numsMask & (1 << i)) != 0) {
                generateCombinations(result, numsMask, targetCount, i + 1, currentMask | (1 << i), n);
            }
        }
    }
}
