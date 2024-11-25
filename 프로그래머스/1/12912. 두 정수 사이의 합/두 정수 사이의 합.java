class Solution {
    public long solution(int a, int b) {
        if (a == b) return a;
        
        long answer = 0;
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = a; i <= b; i++) {
            answer += i;
        }
        return answer;
    }
}