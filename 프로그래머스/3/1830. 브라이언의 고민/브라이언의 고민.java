import java.util.*;

class Word {
    int rule;
    int startIdx;
    int endIdx;
    
    Word(int rule, int startIdx, int endIdx) {
        this.rule = rule;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }
}

class Solution {
    static final String INVALID = "invalid";
    Map<Character, List<Integer>> chars = new LinkedHashMap<>();
    List<Word> words = new ArrayList<>();
    int strStartIdx = 0, preWordStartIdx = -1, preWordEndIdx = -1, preCharStartIdx = -1, preCharEndIdx = -1, wordStartIdx = -1, wordEndIdx = -1, charStartIdx = -1, charEndIdx = -1;
    
    public String solution(String sentence) {
        if (sentence.split(" ").length > 1) return INVALID;
        
        char[] s = sentence.toCharArray();
        findIdxs(s);
        if (!isRight(s)) return INVALID;
        return combineWords(s);
    }
    
    private void findIdxs(char[] s) {
        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            if (isLower(c)) {
                chars.putIfAbsent(c, new ArrayList<>());
                chars.get(c).add(i);
            }
        }
    }
    
    private boolean isLower(char c) {
        return 'a' <= c && c <= 'z';
    }
    
    private boolean isUpper(char c) {
        return 'A' <= c && c <= 'Z';
    }
    
    private boolean isRight(char[] s) {        
        for (List<Integer> positions : chars.values()) {
            boolean isRule1 = true; // 규칙 1인지 확인
            int cnt = positions.size();
            wordStartIdx = charStartIdx = positions.get(0);
            wordEndIdx = charEndIdx = positions.get(cnt - 1);
            
            for (int i = 1; i < cnt; i++) {
                int dist = positions.get(i) - positions.get(i - 1);
                // 소문자 연속이면 불가능
                if (dist < 2) return false;
                
                // 간격이 2보다 크다면 규칙 2 성립
                if (dist > 2) {
                    isRule1 = false;
                    break;
                }
            }
            
            // 소문자가 3개 이상이면 규칙 1 -> isRule1이 false면 불가능
            if (cnt >= 3 && !isRule1) return false;
            
            int rule = -1;
            if (cnt == 1 || cnt >= 3) {
                rule = 1;
                wordStartIdx--;
                wordEndIdx++;
                // 단어 시작이 0보다 작거나 단어 끝이 sentence보다 크다면 불가능
                if (wordStartIdx < 0 || s.length <= wordEndIdx) return false;
            }
            
            if (cnt == 2) rule = isRule1 ? 0 : 2; // 0 -> 규칙 1 or 2
            
            if (preCharStartIdx < charStartIdx && charEndIdx < preCharEndIdx) {
                if (rule == 2) return false;
                continue;
            }
            
            if (preWordEndIdx >= wordStartIdx) return false;
            words.add(new Word(rule, wordStartIdx, wordEndIdx));
            preCharStartIdx = charStartIdx;
            preCharEndIdx = charEndIdx;
            preWordEndIdx = wordEndIdx;
        }
        
        return true;
    }
    
    private String combineWords(char[] s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            int rule = word.rule;
            wordStartIdx = word.startIdx;
            wordEndIdx = word.endIdx;
            if (rule == 0) {
                if ((strStartIdx <= wordStartIdx - 1) && (wordEndIdx + 1 < (i < words.size() - 1 ? words.get(i + 1).startIdx : s.length))) {
                    wordStartIdx--;
                    wordEndIdx++;
                }
            }
            if (strStartIdx < wordStartIdx) sb.append(getStr(s, strStartIdx, wordStartIdx - 1));
            sb.append(getStr(s, wordStartIdx, wordEndIdx));
            strStartIdx = wordEndIdx + 1;
        }
        if (strStartIdx < s.length) sb.append(getStr(s, strStartIdx, s.length - 1));
        return sb.toString().trim();
    }
    
    private String getStr(char[] s, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            char c = s[i];
            if (isUpper(c)) sb.append(c);
        }
        sb.append(" ");
        return sb.toString();
    }
}