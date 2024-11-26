class Solution {
    int answer = 0;
    int[] number;
    
    public int solution(int[] tmp) {
        number = tmp;
        combinations(0, 0, 0);
        return answer;
    }
    
    public void combinations(int start, int total, int r) {
        if (r == 3) {
            if (total == 0) answer++;
            return;
        }
        
        for (int i = start; i < number.length; i++) {
            combinations(i + 1, total + number[i], r + 1);
        }
    }
}