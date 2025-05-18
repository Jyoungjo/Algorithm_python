import java.util.*;

class Solution {
    Map<String, Integer> marginMap = new HashMap<>();
    Map<String, String> referralMap = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            marginMap.put(enroll[i], 0);
            referralMap.put(enroll[i], referral[i]);
        }
        
        for (int i = 0; i < seller.length; i++) {
            dfs(seller[i], amount[i] * 100);
        }
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = marginMap.get(enroll[i]);
        }
        
        return answer;
    }
    
    private void dfs(String seller, int profit) {  
        String ref = referralMap.get(seller);
        int yours = (int) (profit * 0.1);
        marginMap.put(seller, marginMap.get(seller) + profit - yours);
        
        if (!ref.equals("-") && profit >= 10) dfs(referralMap.get(seller), yours);
    }
}