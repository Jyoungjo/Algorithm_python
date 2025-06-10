class Solution {
    public int solution(String s) {
        // 길이 변화 7 ~ 1, 시작 idx 변화
        for (int len = s.length(); len > 0; len--) {
            for (int i = 0; i <= s.length() - len; i++) {
                boolean isFind = true;
                for (int j = i; j < i + len / 2; j++) {
                    if (s.charAt(j) != s.charAt(len - 1 - j + 2 * i)) {
                        isFind = false;
                        break;
                    }
                }
                
                if (isFind) return len;
            }
        }
        return 1;
    }
}