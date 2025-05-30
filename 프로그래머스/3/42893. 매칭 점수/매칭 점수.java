import java.util.*;

class Web {
    int idx;
    String[] ext_urls;
    double score, link_score;
    
    public Web(int idx) {
        this.idx = idx;
    }
    
    public void addExtUrls(String[] htmls) {
        List<String> result = new ArrayList<>();
        Arrays.stream(htmls).forEach(h -> result.add(h.split("\"")[0]));
        this.ext_urls = result.toArray(String[]::new);
    }
    
    public void addScore(double score) {
        this.score = score;
    }
}

class Solution {
    Map<String, Web> result = new HashMap<>();
    
    public int solution(String word, String[] pages) {
        for (int i = 0; i < pages.length; i++) {
            String now = pages[i];
            // URL 등록
            String url = now.split("<meta property=\"og:url\" content=\"https://")[1].split("\"")[0];
            Web web = new Web(i);
            result.put(url, web);
            // 외부 링크 등록
            String[] splited = now.split("<a href=\"https://");
            web.addExtUrls(Arrays.copyOfRange(splited, 1, splited.length));
            // 기본 점수 계산
            String[] tmp = now.split("<body>")[1].split("<a href=\"https://");
            double score = 0;
            for (String s : tmp) {
                for (int j = 0; j <= s.length() - word.length(); j++) {
                    if (isMatch(s, word, j, s.length())) score += 1;
                }
            }
            web.addScore(score);
        }
        
        // 링크 점수 계산
        for (String key : result.keySet()) {
            Web web = result.get(key);
            String[] ext = web.ext_urls;
            for (String e : ext) {
                Web otherWeb = result.get(e);
                if (otherWeb == null) continue;
                otherWeb.link_score += (web.score / ext.length);
            }
        }
        
        // 매칭 점수 계산
        int answer = -1; double match_score = -1;
        for (String key : result.keySet()) {
            Web web = result.get(key);
            double m_score = web.score + web.link_score;
            if (match_score < m_score) {
                match_score = m_score;
                answer = web.idx;
            }
        }
        
        return answer;
    }
    
    private boolean isMatch(String s, String word, int i, int len) {
        String target = s.substring(i, i + word.length()).toLowerCase();
        if (!target.equals(word.toLowerCase())) return false;
        // 앞 글자 확인
        if (i > 0 && Character.isLetter(s.charAt(i - 1))) return false;
        // 뒤 글자 확인
        if (i + word.length() < len && Character.isLetter(s.charAt(i + word.length()))) return false;
        
        return true;
    }
}