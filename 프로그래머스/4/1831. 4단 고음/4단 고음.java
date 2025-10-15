class Solution {
    public int solution(int n) {
        return solve(n, 0);
    }
    
    private int solve(int n, int added) {
        if (Math.pow(3, added / 2) > n) return 0;
        
        int result = 0;
        if (n == 3) {
            if (added == 2) return 1;
            return 0;
        } else if (n > 3) {
            if (added >= 2 && n % 3 == 0) result += solve(n / 3, added - 2);
            result += solve(n - 1, added + 1);
        }
        
        return result;
    }
}