import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        Integer[] nums = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Arrays.sort(nums, (o1, o2) -> {
            String str1 = String.valueOf(o1), str2 = String.valueOf(o2);
            return (str2 + str1).compareTo(str1 + str2);
        });
                
        StringBuilder sb = new StringBuilder();
        for (int n : nums) sb.append(n);
        return sb.toString().startsWith("0") ? "0" : sb.toString();
    }
}