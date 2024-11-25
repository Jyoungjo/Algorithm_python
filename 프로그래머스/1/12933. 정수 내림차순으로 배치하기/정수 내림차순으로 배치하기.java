import java.util.*;


class Solution {
    public long solution(long n) {
        // 숫자를 문자열로 변환 후 배열 생성
        char[] arr = String.valueOf(n).toCharArray();
        
        // 내림차순 정렬
        Arrays.sort(arr);
        
        // 정렬된 배열을 역순으로 순회하며 숫자 재구성
        StringBuilder sb = new StringBuilder(new String(arr));
        sb.reverse();
        
        return Long.parseLong(sb.toString());
    }
}