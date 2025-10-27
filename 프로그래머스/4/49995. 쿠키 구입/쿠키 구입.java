class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int len = cookie.length;

        for (int mid = 0; mid < len - 1; mid++) {
            int l = mid, r = mid + 1;
            int lSum = cookie[l], rSum = cookie[r];
            
            while (0 <= l && r < len) {
                if (lSum == rSum) answer = Math.max(answer, lSum);
                
                if (0 < l && lSum <= rSum) lSum += cookie[--l];
                else if (r < len - 1 && lSum >= rSum) rSum += cookie[++r];
                else break;
            }
        }
        
        return answer;
    }
}