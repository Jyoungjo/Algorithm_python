class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin) + 1];
        int idx = 0;
        for (long s = begin; s <= end; s++) {
            if (s == 1) {
                answer[idx++] = 0;
                continue;
            }
            
            answer[idx++] = findPrimeNumber(s);
        }
        
        return answer;
    }
    
    private int findPrimeNumber(long num) {
        int max = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                max = i;
                if (num / i <= 10_000_000) return (int) num / i;
            }
        }
        
        return max;
    }
}