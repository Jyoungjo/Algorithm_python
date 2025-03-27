import java.util.*;

class Solution {
    final char UP = 'U', DOWN = 'D', LEFT = 'L', RIGHT = 'R';
    
    public int solution(String dirs) {
        int answer = 0;
        
        Map<Character, int[]> map = new HashMap<>();
        map.put(UP, new int[]{-1, 0});
        map.put(DOWN, new int[]{1, 0});
        map.put(LEFT, new int[]{0, -1});
        map.put(RIGHT, new int[]{0, 1});
        
        int y = 0, x = 0;
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < dirs.length(); i++) {
            int[] dir = map.get(dirs.charAt(i));
            int ny = y + dir[0], nx = x + dir[1];
            if (!isWithinRange(ny, nx)) continue;
            
            String s = y + "," + x + ":" + ny + "," + nx;
            String ss = ny + "," + nx + ":" + y + "," + x;
            if (!visited.contains(s) && !visited.contains(ss)) {
                answer++;
                visited.add(s);
                visited.add(ss);
            }
            y = ny;
            x = nx;
        }
        
        return answer;
    }
    
    private boolean isWithinRange(int y, int x) {
        return -5 <= y && y <= 5 && -5 <= x && x <= 5;
    }
}