class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] arr = s.toCharArray();
        for (int len = s.length(); len >= 1; len--) {
            for (int idx = 0; idx <= s.length() - len; idx++) {
                boolean isPalindrome = true;
                for (int i = idx; i < idx + len / 2; i++) {
                    if (arr[i] != arr[idx + len - 1 - (i - idx)]) {
                        isPalindrome = false;
                        break;
                    }
                }
                if (isPalindrome) return len;
            }
        }
        
        return answer;
    }
}