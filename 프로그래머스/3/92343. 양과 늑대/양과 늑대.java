import java.util.*;

class Solution {
    final int ROOT_NODE = 0;
    
    List<Integer>[] graph;
    int N;
    int answer = Integer.MIN_VALUE;
    
    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        Set<Integer> destinations = new HashSet<>(graph[ROOT_NODE]);
        backtracking(info, destinations, ROOT_NODE, 1, 0);
        return answer;
    }
    
    private void init(int[] info, int[][] edges) {
        N = info.length;
        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
    }
    
    private void backtracking(int[] info, Set<Integer> destinations, int curNode, int sheep, int wolf) {
        if (sheep <= wolf) return;
        
        answer = Math.max(answer, sheep);
        
        for (int next : destinations) {
            Set<Integer> nextDest = new HashSet<>(destinations);
            nextDest.remove(next);
            nextDest.addAll(graph[next]);

            if (info[next] == 0) backtracking(info, nextDest, next, sheep + 1, wolf);
            else backtracking(info, nextDest, next, sheep, wolf + 1);
        }
    }
}