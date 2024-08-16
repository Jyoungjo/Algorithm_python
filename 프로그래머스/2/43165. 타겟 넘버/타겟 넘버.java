class Solution {
    int answer = 0;
    
    public void findCount(int[] numbers, int target, int idx, int value) {
        if (idx == numbers.length) {
            if (value == target) {
                this.answer++;
            }
            return;
        }
        
        findCount(numbers, target, idx + 1, value + numbers[idx]);
        findCount(numbers, target, idx + 1, value - numbers[idx]);
    }
    
    
    public int solution(int[] numbers, int target) {
        findCount(numbers, target, 0, 0);
        return answer;
    }
}