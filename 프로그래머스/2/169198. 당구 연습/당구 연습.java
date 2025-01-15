import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < balls.length; i++) {
            int min = Integer.MAX_VALUE;
            int len;
            
            int endX = balls[i][0], endY = balls[i][1];
            
            // 좌
            if (!(startY == endY && startX >= endX)) {
                min = Math.min(min, getDistance(startX, startY, -endX, endY));
            }
            // 우
            if (!(startY == endY && startX <= endX)) {
                min = Math.min(min, getDistance(startX, startY, 2 * m - endX, endY));
            }
            // 상
            if (!(startX == endX && startY <= endY)) {
                min = Math.min(min, getDistance(startX, startY, endX, 2 * n - endY));
            }
            // 하
            if (!(startX == endX && startY >= endY)) {
                min = Math.min(min, getDistance(startX, startY, endX, -endY));
            }
            
            list.add(min);
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int getDistance(int sX, int sY, int eX, int eY) {
        return (int) (Math.pow(sX - eX, 2) + Math.pow(sY - eY, 2));
    }
}