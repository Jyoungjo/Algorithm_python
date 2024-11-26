class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0, maxH = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            int width = sizes[i][0];
            int height = sizes[i][1];
            
            if (width < height) {
                int tmp = width;
                width = height;
                height = tmp;
            }
            
            if (maxW < width) maxW = width;
            if (maxH < height) maxH = height;
        }
        
        return maxW * maxH;
    }
}