import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        
        for (int[] p : data) {
            xList.add(p[0]);
            yList.add(p[1]);
        }
        
        List<Integer> uniqueXList = new ArrayList<>(new HashSet<>(xList));
        List<Integer> uniqueYList = new ArrayList<>(new HashSet<>(yList));
        
        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);
        
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            data[i][0] = uniqueXList.indexOf(xList.get(i));
            data[i][1] = uniqueYList.indexOf(yList.get(i));
            
            dp[data[i][1]][data[i][0]] = 1;
        }
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r - 1 >= 0) dp[r][c] += dp[r - 1][c];
                if (c - 1 >= 0) dp[r][c] += dp[r][c - 1];
                if (r - 1 >= 0 && c - 1 >= 0) dp[r][c] -= dp[r - 1][c - 1];
            }
        }
        
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int r1 = Math.min(data[i][1], data[j][1]);
                int c1 = Math.min(data[i][0], data[j][0]);
                int r2 = Math.max(data[i][1], data[j][1]);
                int c2 = Math.max(data[i][0], data[j][0]);
                
                if (r1 == r2 || c1 == c2) continue;
                
                int cnt = dp[r2 - 1][c2 - 1] - dp[r2 - 1][c1] - dp[r1][c2 - 1] + dp[r1][c1];
                
                if (cnt == 0) answer++;
            }
        }
        
        return answer;
    }
}