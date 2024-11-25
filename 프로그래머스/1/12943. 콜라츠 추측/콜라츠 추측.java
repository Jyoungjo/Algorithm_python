class Solution {
    public int solution(int num) {
        int answer = 0;
        long target = num;
        
        for (int i = 0; i < 500; i++) {
            if (target == 1) return answer;
            
            if (target % 2 == 0) {
                target /= 2;
            } else {
                target = target * 3 + 1;
            }
            
            answer++;
        }
        
        return -1;
    }
}