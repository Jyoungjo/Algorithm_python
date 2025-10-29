class Solution {
    int answer = 0;
    
    public int solution(int[] nums) {
        combinations(nums, 0, 0, 0);
        return answer;
    }
    
    private void combinations(int[] nums, int depth, int sum, int now) {
        if (depth == 3) {
            if (isPrime(sum)) answer++;
            return;
        }
        
        for (int i = now; i < nums.length; i++) {
            combinations(nums, depth + 1, sum + nums[i], i + 1);
        }
    }
    
    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
}