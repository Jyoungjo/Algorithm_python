import java.util.*;

class Solution {
    int[][] tree;
    int[] arr;
    int v, answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        Set<Integer> set = new HashSet<>();
        set.add(0);
        dfs(1, 0, set);
        return answer;
    }
    
    private void dfs(int sheep, int wolf, Set<Integer> set) {
        if (sheep <= wolf) return;
        answer = Math.max(answer, sheep);
        
        for (int now : set) {
            if (tree[now][0] != -1 && !set.contains(tree[now][0])) {
                Set<Integer> tmpSet = new HashSet<>(set);
                tmpSet.add(tree[now][0]);
                if (arr[tree[now][0]] == 0) dfs(sheep + 1, wolf, tmpSet);
                else dfs(sheep, wolf + 1, tmpSet);
            }
            
            if (tree[now][1] != -1 && !set.contains(tree[now][1])) {
                Set<Integer> tmpSet = new HashSet<>(set);
                tmpSet.add(tree[now][1]);
                if (arr[tree[now][1]] == 0) dfs(sheep + 1, wolf, tmpSet);
                else dfs(sheep, wolf + 1, tmpSet);
            }
        }
    }
    
    private void init(int[] info, int[][] edges) {
        v = info.length;
        tree = new int[v][2];
        arr = info;
        
        for (int i = 0; i < v; i++) {
            Arrays.fill(tree[i], -1);
        }
        
        for (int[] edge : edges) {
            int s = edge[0], e = edge[1];
            if (tree[s][0] == -1) tree[s][0] = e;
            else tree[s][1] = e;
        }
    }
}