class Solution {
    boolean solution(String s) {
        String str = s.toLowerCase();
        int pCnt = 0, yCnt = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if (c == 'p') pCnt++;
            if (c == 'y') yCnt++;
        }
        
        if (pCnt == 0 && yCnt == 0) return true;
        return pCnt == yCnt;
    }
}