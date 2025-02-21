import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        /*
            일정 금액 지불 -> 10일 회원 자격 부여
            매일 한 가지 제품 할인 -> 하루에 하나씩만 구매 가능
            자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속 일치하는 거에 맞춰서 가입하려고 함
            
            리턴: 원하는 제품을 모두 할인 받을 수 있는 등록 날짜의 총 일수 (없으면 0)
        */
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        for (int i = 0; i < 10; i++) {
            result.put(discount[i], result.getOrDefault(discount[i], 0) + 1);
        }
        
        int s = 0;
        while (s <= discount.length - 10) {
            if (s - 1 >= 0) {
                result.put(discount[s - 1], result.get(discount[s - 1]) - 1);
                result.put(discount[s + 9], result.getOrDefault(discount[s + 9], 0) + 1);
                if (result.get(discount[s - 1]) == 0) result.remove(discount[s - 1]);
            }
            
            if (isCorrect(map, result, want)) answer++;
            s++;
        }
        
        return answer;
    }
    
    private boolean isCorrect(Map<String, Integer> map, Map<String, Integer> result, String[] want) {
        for (String s : want) {
            if (map.get(s) != result.get(s)) return false;
        }
        return true;
    }
}