class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        char[] cArr = s.toCharArray();
        for (int len = s.length(); len >= 1; len--) {
            for (int i = 0; i < s.length() - len + 1; i++) {
                boolean isAnswer = true;
                for (int j = i; j < i + len / 2; j++) {
                    if (cArr[j] != cArr[len - 1 - j + 2 * i]) {
                        isAnswer = false;
                        break;
                    }
                }
                if (isAnswer) return len;
            }
        }

        return answer;
    }
}