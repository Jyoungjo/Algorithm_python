import java.util.*;


class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        init(nodes, edges);
        Set<Integer> visited = new HashSet<>();
        for (int node : nodes) {
            if (visited.contains(node)) continue;
            int[] result = new int[4]; // 홀, 짝, 역홀, 역짝
            dfs(node, visited, result);
            checkAnswer(answer, result);
        }
        return answer;
    }
    
    private void checkAnswer(int[] answer, int[] result) {
        if ((result[0] == 1 && result[1] == 0) || (result[0] == 0 && result[1] == 1)) answer[0]++;
        if ((result[2] == 1 && result[3] == 0) || (result[2] == 0 && result[3] == 1)) answer[1]++;
    }
    
    private void dfs(int now, Set<Integer> visited, int[] result) {
        calNode(now, result);
        visited.add(now);
        for (int next : map.get(now)) {
            if (visited.contains(next)) continue;
            dfs(next, visited, result);
        }
    }
    
    private void calNode(int now, int[] result) {
        if (now % 2 == 1 && map.get(now).size() % 2 == 1) result[0]++;
        if (now % 2 == 0 && map.get(now).size() % 2 == 0) result[1]++;
        if (now % 2 == 1 && map.get(now).size() % 2 == 0) result[2]++;
        if (now % 2 == 0 && map.get(now).size() % 2 == 1) result[3]++;
    }
    
    private void init(int[] nodes, int[][] edges) {
        for (int n : nodes) {
            map.put(n, new ArrayList<>());
        }
        
        for (int[] e : edges) {
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
        }
    }
}