import java.util.*;

class Solution {
    List<Set<Integer>> dp = new ArrayList<>(); // 8보다 크면 return -1
    
    public int solution(int N, int number) {
        for (int i = 1; i < 9; i++) {
            Set<Integer> tmpSet = new HashSet<>();
            tmpSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            
            for (int j = 1; j < i; j++) {
                for (int n1 : dp.get(j - 1)) {
                    for (int n2 : dp.get(i - j - 1)) {
                        tmpSet.add(n1 + n2);
                        tmpSet.add(n1 - n2);
                        tmpSet.add(n1 * n2);
                        if (n2 != 0) tmpSet.add(n1 / n2);
                    }
                }
            }
            
            for (int x : tmpSet) {
                if (x == number) return i;
            }
            
            dp.add(tmpSet);
        }
        
        return -1;
    }
}