import java.util.*;

class Solution {
    Map<String, Integer> result = new HashMap<>();
    Map<String, String> map = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        init(enroll, referral);
        for (int i = 0; i < seller.length; i++) {
            dfs(seller[i], amount[i] * 100);
        }
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = result.get(enroll[i]);
        }
        return answer;
    }
    
    private void dfs(String sel, int amo) {
        /*
            수수료 = 현재 비용 * 0.1
            수익 = 비용 - 수수료
        */
        String parent = map.get(sel);
        int fee = (int) (amo * 0.1);
        int revenue = amo - fee;
        
        // System.out.printf("seller: %s, val: %d\n", sel, revenue);
        result.put(sel, result.get(sel) + revenue);
        if (!parent.equals("-") && amo >= 10) dfs(parent, fee);
    }
    
    private void init(String[] enroll, String[] referral) {
        for (int i = 0; i < enroll.length; i++) {
            result.put(enroll[i], 0);
            map.put(enroll[i], referral[i]);
        }
    }
}