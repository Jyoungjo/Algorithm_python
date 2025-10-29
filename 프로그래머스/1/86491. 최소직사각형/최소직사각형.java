class Solution {
    public int solution(int[][] sizes) {
        int answer = 0, w = -1, h = -1;
        for (int i = 0; i < sizes.length; i++) {
            int max = -1, min = 1001;
            for (int j = 0; j < 2; j++) {
                max = Math.max(max, sizes[i][j]);
                min = Math.min(min, sizes[i][j]);
            }
            
            w = Math.max(w, max);
            h = Math.max(h, min);
        }
        
        return w * h;
    }
}