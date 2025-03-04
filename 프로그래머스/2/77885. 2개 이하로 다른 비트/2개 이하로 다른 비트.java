class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];
            String target = Long.toBinaryString(x);
            
            if (x % 2 == 0) {
                answer[i] = x + 1;
                continue;
            } else {
                int idx = target.lastIndexOf("0");
                if (idx == -1) answer[i] = Long.parseLong("10" + target.substring(1), 2);
                else answer[i] = Long.parseLong(target.substring(0, idx) + "10" + target.substring(idx + 2), 2);
            }
        }
        return answer;
    }
}