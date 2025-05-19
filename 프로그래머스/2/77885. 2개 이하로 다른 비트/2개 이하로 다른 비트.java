import java.util.*;
import java.util.stream.*;

class Solution {
    public long[] solution(long[] numbers) {
        List<Long> answer = new ArrayList<>();
        for (long num : numbers) {
            String bit = Long.toBinaryString(num);
            if (num % 2 == 0) answer.add(Long.parseLong(bit.substring(0, bit.length() - 1) + "1", 2));
            else {
                int idx = bit.lastIndexOf("0");
                if (idx == -1) answer.add(Long.parseLong("10" + bit.substring(1), 2));
                else answer.add(Long.parseLong(bit.substring(0, idx) + "10" + bit.substring(idx + 2), 2));
            }
        }
        return answer.stream().mapToLong(Long::longValue).toArray();
    }
}