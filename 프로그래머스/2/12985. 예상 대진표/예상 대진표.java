class Solution {
    int answer = 0;
    
    public int solution(int n, int a, int b) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        recursive(a, b, 1);
        return answer;
    }
    
    private void recursive(int a, int b, int round) {
        if (a + 1 == b && a / 2 != b / 2 ) {
            answer = round;
            return;
        }
        
        a = a % 2 == 1 ? a / 2 + 1 : a / 2;
        b = b % 2 == 1 ? b / 2 + 1 : b / 2;
        
        System.out.printf("a: %d, b: %d\n", a, b);
        
        recursive(a, b, round + 1);
    }
}