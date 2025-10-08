class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int size = cookie.length;
        
        for (int mid = 0; mid < size - 1; mid++) {
            int lsum = cookie[mid], rsum = cookie[mid + 1];
            int l = mid, r = mid + 1;
            
            while (0 <= l && r < size) {
                if (lsum == rsum) answer = Math.max(answer, lsum);
                
                if (lsum <= rsum && l > 0) lsum += cookie[--l];
                else if (lsum >= rsum && r < size - 1) rsum += cookie[++r];
                else break;
            }
        }
        
        return answer;
    }
}