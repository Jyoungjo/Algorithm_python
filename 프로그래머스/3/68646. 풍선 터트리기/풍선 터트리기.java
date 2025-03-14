class Solution {
    public int solution(int[] a) {
        int answer = 0, l = a[0], r = a[a.length - 1];
        int[] left = new int[a.length], right = new int[a.length];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < l) l = a[i];
            left[i] = l;
        }
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < r) r = a[i];
            right[i] = r;
        }
        
        if (a.length == 1) return 1;
        answer += 2; // 양 옆은 무조건 살 수 있기 때문에
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] > left[i] && a[i] > right[i]) continue;
            answer++;
        }
        return answer;
    }
}