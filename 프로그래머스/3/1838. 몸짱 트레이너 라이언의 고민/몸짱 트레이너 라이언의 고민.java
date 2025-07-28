import java.util.*;

class Solution {
    int N, M;
    
    public int solution(int n, int m, int[][] timetable) {
        N = n; M = m;
        
        int cnt = cntMember(timetable);
        if (cnt <= 1) return 0;
        
        int l = 0, r = (n - 1) * 2, answer = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (canAssign(cnt, mid)) {
                answer = mid;
                l = mid + 1; // 최대한 멀리 떨어져야 하므로
            } else r = mid - 1;
        }
        
        return answer;
    }
    
    private int cntMember(int[][] timetable) {
        List<int[]> list = new ArrayList<>();
        for (int[] t : timetable) {
            list.add(new int[]{t[0], 1});
            list.add(new int[]{t[1], -1});
        }
        
        list.sort((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });
        
        int ans = 0, max = 0;
        for (int[] t : list) {
            max += t[1];
            ans = Math.max(ans, max);
        }
        
        return ans;
    }
    
    private boolean canAssign(int member, int dist) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<int[]> placed = new ArrayList<>();
                placed.add(new int[]{i, j});
                
                for (int r = i; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (r == i && c <= j) continue;
                        
                        boolean flag = true;
                        for (int[] p : placed) {
                            if (Math.abs(p[0] - r) + Math.abs(p[1] - c) < dist) {
                                flag = false;
                                break;
                            }
                        }
                        
                        if (flag) {
                            placed.add(new int[]{r, c});
                            if (placed.size() == member) return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}