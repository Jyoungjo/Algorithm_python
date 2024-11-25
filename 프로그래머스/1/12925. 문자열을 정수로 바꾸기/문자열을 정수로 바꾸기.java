class Solution {
    public int solution(String s) {
        boolean flag = false;
        if (s.startsWith("-")) {
            flag = true;
            s = s.substring(1);
        }
        
        return flag ? -Integer.parseInt(s) : Integer.parseInt(s);
    }
}