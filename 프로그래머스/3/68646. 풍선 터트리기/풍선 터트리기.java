class Solution {
    public int solution(int[] a) {
        int len = a.length;
        int[] left = new int[len], right = new int[len];
        
        left[len - 1] = a[len - 1];
        for (int i = len - 2; i > 0; i--) {
            left[i] = Math.min(left[i + 1], a[i]);
        }
        right[0] = a[0];
        for (int i = 1; i < len - 1; i++) {
            right[i] = Math.min(right[i - 1], a[i]);
        }
        
        int answer = 2;
        for (int i = 1; i < len - 1; i++) {
            if (!(a[i] > left[i + 1] && a[i] > right[i - 1])) answer++;
        }
        
        return answer;
    }
}