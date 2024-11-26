class Solution {
    public String solution(String s, int n) {
        String answer = "";
        int gap = (int) 'z' - (int) 'a' + 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                answer += " ";
                continue;
            }

            int target = s.charAt(i) + n;

            if (target - n >= (int) 'a' && target - n <= (int) 'z') {
                if (target > (int) 'z') target -= gap;
            } else if (target - n >= (int) 'A' && target - n <= (int) 'Z') {
                if (target > (int) 'Z') target -= gap;
            }

            answer += Character.toString(target);
        }
        return answer;
    }
}