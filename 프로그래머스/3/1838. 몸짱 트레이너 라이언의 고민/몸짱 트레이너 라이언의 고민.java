import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] timetable) {
        if (timetable.length == 1) return 0;
        
        int people = findMaxPeople(timetable);
        return assign(n, people);
    }
    
    private int findMaxPeople(int[][] timetable) {
        List<int[]> events = new ArrayList<>();
        for (int[] t : timetable) {
            events.add(new int[]{t[0], 1});
            events.add(new int[]{t[1], -1});
        }
        
        events.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return o2[1] - o1[1];
            return o1[0] - o2[0];
        });
        
        int max = -1, cnt = 0;
        for (int[] e : events) {
            cnt += e[1];
            max = Math.max(max, cnt);
        }
        
        return max;
    }
    
    private int assign(int n, int people) {
        int l = 1, r = n * 2, res = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (isAssign(n, mid, people)) {
                l = mid + 1;
                res = mid;
            } else r = mid - 1;
        }
        
        return res;
    }
    
    private boolean isAssign(int n, int dist, int people) {
        int[][] locker = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                List<int[]> placed = new ArrayList<>();
                placed.add(new int[]{r, c});
                
                for (int i = r; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (r == i && c >= j) continue;
                        
                        boolean flag = true;
                        for (int[] p : placed) {
                            if (Math.abs(p[0] - i) + Math.abs(p[1] - j) < dist) {
                                flag = false;
                                break;
                            }
                        }
                        
                        if (flag) {
                            placed.add(new int[]{i, j});
                            if (placed.size() == people) return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}