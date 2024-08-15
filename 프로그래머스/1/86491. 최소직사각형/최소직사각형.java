class Solution {
    public int solution(int[][] sizes) {
        int w = Integer.MIN_VALUE, h = Integer.MIN_VALUE;
        
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            
            if (sizes[i][0] > w) {
                w = sizes[i][0];
            }
            
            if (sizes[i][1] > h) {
                h = sizes[i][1];
            }
        }
        
        return w * h;
    }
}