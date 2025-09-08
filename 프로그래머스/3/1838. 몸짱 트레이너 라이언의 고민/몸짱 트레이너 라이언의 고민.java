import java.util.*;

class Solution {
    int N, M;
    
    public int solution(int n, int m, int[][] timetable) {
        if (m == 1) return 0;
        
        N = n; M = m;
        int people = findOverlappedPeople(timetable);
        return calDist(people);
    }
    
    private int findOverlappedPeople(int[][] timetable) {
        Arrays.sort(timetable, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        
        int people = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            int tmp = 0;
            int[] now = timetable[i];
            
            for (int j = i + 1; j < M; j++) {
                int[] next = timetable[j];
                
                if (next[1] < now[0] || now[1] < next[0]) continue;
                tmp++;
            }
            people = Math.max(tmp, people);
        }
        
        return people == 0 ? people : people + 1;
    }
    
    private int calDist(int people) {
        int l = 0, r = (N - 1) * 2, result = 0;
        
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (assign(mid, people)) {
                l = mid + 1;
                result = mid;
            } else r = mid - 1;
        }
        
        return result;
    }
    
    private boolean assign(int dist, int people) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                List<int[]> lockers = new ArrayList<>();
                lockers.add(new int[]{r, c});
                
                for (int i = r; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i == r && j <= c) continue;
                        
                        boolean isAssigned = true;
                        for (int[] l : lockers) {
                            if (Math.abs(l[0] - i) + Math.abs(l[1] - j) < dist) {
                                isAssigned = false;
                                break;
                            }
                        }
                        
                        if (isAssigned) {
                            lockers.add(new int[]{i, j});
                            if (lockers.size() == people) return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}