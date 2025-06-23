import java.util.*;

class Solution {
    int N;
    
    public int solution(int n, int m, int[][] timetable) {
        N = n;
        int max = calMax(timetable);
        if (max <= 1) return 0;
        
        return solve(max);
    }
    
    private int calMax(int[][] timetable) {
        int[] people = new int[1321];
        
        for (int[] time : timetable) {
            for (int i = time[0]; i <= time[1]; i++) people[i]++;
        }
        
        int max = 0;
        for (int i = 600; i < 1321; i++) max = Math.max(max, people[i]);
        
        return max;
    }
    
    private int solve(int people) {
        int l = 1, r = N * 2, res = 0;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (canAssign(people, mid)) {
                res = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        
        return res;
    }
    
    private boolean canAssign(int people, int dist) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<int[]> assigned = new ArrayList<>();
                assigned.add(new int[]{i, j});
                
                for (int row = i; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        boolean flag = true;
                        if (row == i && col <= j) continue;
                        
                        for (int[] a : assigned) {
                            if (Math.abs(a[0] - row) + Math.abs(a[1] - col) < dist) {
                                flag = false;
                                break;
                            }
                        }
                        
                        if (flag) {
                            assigned.add(new int[]{row, col});
                            if (assigned.size() == people) return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}