import java.util.*;

class Solution {
    int M, N;
    
    public int solution(int n, int m, int[][] timetable) {
        M = m; N = n;
        
        int relatedMembers = findRelations(timetable); // 제일 많이 겹쳤을 때만 체크
        if (relatedMembers <= 1) return 0;
        
        return calDist(relatedMembers);
    }
    
    private int findRelations(int[][] timetable) {
        List<int[]> events = new ArrayList<>();
        for (int[] t : timetable) {
            events.add(new int[]{t[0], 1});  // 입실
            events.add(new int[]{t[1], -1}); // 퇴실
        }

        events.sort((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1]; // 입실 우선
            return a[0] - b[0];
        });

        int cnt = 0, max = 0;
        for (int[] e : events) {
            cnt += e[1];
            max = Math.max(max, cnt);
        }
        return max;
    }
    
    private int calDist(int members) {
        int l = 0, r = (N - 1) * 2, result = 0;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (canAssign(members, mid)) {
                result = mid;
                l = mid + 1;
            }
            else r = mid - 1;
        }
        
        return result;
    }
    
    private boolean canAssign(int members, int dist) {        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<int[]> placed = new ArrayList<>();
                placed.add(new int[]{i, j});
                
                for (int row = i; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        boolean canPlace = true;
                        
                        if (row == i && col <= j) continue;
                        
                        for (int[] p : placed) {
                            if (Math.abs(p[0] - row) + Math.abs(p[1] - col) < dist) {
                                canPlace = false;
                                break;
                            }
                        }
                
                        if (canPlace) {
                            placed.add(new int[]{row, col});
                            if (placed.size() == members) return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}