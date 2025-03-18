class Solution {
    public long solution(int w, int h) {
        long all = (long) w * h, repeatNum = gcd(w, h);
        long ww = (long) w / repeatNum, hh = (long) h / repeatNum, notUsed = ww + hh - 1;
        return all - notUsed * repeatNum;
    }
    
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}