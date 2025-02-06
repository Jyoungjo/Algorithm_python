import java.util.*;


class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    boolean[] isOn;
    int answer = 0;
    
    public int solution(int n, int[][] lighthouse) {
        isOn = new boolean[n + 1];
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] a : lighthouse) {
            graph.get(a[0]).add(a[1]);
            graph.get(a[1]).add(a[0]);
        }
        
        dfs(1, 1);
        
        return answer;
    }
    
    private void dfs(int parent, int node) {
        for (int child : graph.get(node)) {
            if (parent == child) continue;
            
            dfs(node, child);
            
            if (!isOn[node] && !isOn[child]) {
                isOn[node] = true;
                answer++;
            }
        }
    }
}