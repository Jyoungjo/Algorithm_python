class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        int i = 0;
        for (String name : seoul) {
            if (name.equals("Kim")) {
                return String.format("김서방은 %d에 있다", i);
            }
            i++;
        }
        return answer;
    }
}