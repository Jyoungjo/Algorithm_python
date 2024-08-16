import java.util.*;

class Solution {
    boolean[] visited;
    List<String> result = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs(tickets, "ICN", "ICN", 0);
        Collections.sort(result);
        return result.get(0).split(" ");
    }
    
    public void dfs(String[][] tickets, String start, String route, int cnt) {
        if (cnt == tickets.length) {
            result.add(route);
        } else {
            for (int i = 0; i < tickets.length; i++) {
                if (!visited[i] && tickets[i][0].equals(start)) {
                    visited[i] = true;
                    dfs(tickets, tickets[i][1], route + " " + tickets[i][1], cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}