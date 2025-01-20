import java.util.*;


class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            long target = numbers[i];
            String binaryString = convertToBinaryNum(Long.toString(target, 2));
            answer.add(dfs(binaryString, 0, binaryString.length() - 1) ? 1 : 0);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private String convertToBinaryNum(String bs) {
        int n = 0;
        while (bs.length() > ((2 << n) - 1)) {
            n++;
        }
            
        return "0".repeat(((2 << n) - 1) - bs.length()) + bs;
    }
    
    private boolean dfs(String bs, int start, int end) {
        if (start == end) return true;
        
        int mid = (start + end) / 2;
        
        if (bs.charAt(mid) != '1') {
            return isEmptyTree(bs, start, mid - 1) && isEmptyTree(bs, mid + 1, end);
        }
        
        return dfs(bs, start, mid - 1) && dfs(bs, mid + 1, end);
    }
    
    private boolean isEmptyTree(String bs, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (bs.charAt(i) == '1') return false;
        }
        
        return true;
    }
}

// System.out.println();