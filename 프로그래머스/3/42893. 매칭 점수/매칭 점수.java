import java.util.*;
import java.util.stream.*;

class WebPage {
    int idx;
    String url;
    String[] ext_urls;
    double score, link_score;
    
    public WebPage(int idx, double score, String url, String[] ext_urls) {
        this.idx = idx;
        this.score = score;
        this.url = url;
        this.ext_urls = ext_urls;
    }
}

class Solution {
    public int solution(String word, String[] pages) {
        Map<String, WebPage> map = new HashMap<>();
        
        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            
            // 해당 페이지 url 찾기
            String url = findUrl(page);
            
            // 해당 페이지 기본 점수 찾기
            String body = page.split("<body>")[1].split("</body>")[0].toLowerCase();
            double score = calScore(body, word.toLowerCase());
            
            // 해당 페이지 외부 링크 찾기
            String[] ext_urls = findExtUrls(page);
            
            WebPage web_page = new WebPage(i, score, url, ext_urls);
            map.put(url, web_page);
        }
        
        for (String url : map.keySet()) {
            WebPage page = map.get(url);
            for (String ext : page.ext_urls) {
                if (map.containsKey(ext)) {
                    WebPage extPage = map.get(ext);
                    extPage.link_score += (page.score / page.ext_urls.length);
                }
            }
        }
        
        int answer = -1;
        double max = -1;
        for (String url : map.keySet()) {
            WebPage page = map.get(url);
            if (max < page.score + page.link_score) {
                max = page.score + page.link_score;
                answer = page.idx;
            } else if (max == page.score + page.link_score && answer > page.idx) {
                answer = page.idx;
            }
        }
        
        return answer;
    }
    
    private String findUrl(String page) {
        return page.split("<meta property=\"og:url\" content=\"")[1].split("\"")[0];
    }
    
    private double calScore(String body, String word) {
        double score = 0;
        int wIdx = 0;
        for (int j = 0; j < body.length(); j++) {
            if (wIdx == word.length()) {
                if ('a' > body.charAt(j) || body.charAt(j) > 'z') score++;
                wIdx = 0;
                continue;
            }
                
            if (wIdx < word.length()) {
                if (body.charAt(j) == word.charAt(wIdx)) wIdx++;
                else wIdx = 0;
            }
        }
        
        return score;
    }
    
    private String[] findExtUrls(String page) {
        List<String> ext_urls = new ArrayList<>();
        
        String[] exts = page.split("<a href=\"");
        for (int j = 1; j < exts.length; j++) ext_urls.add(exts[j].split("\"")[0]);
        
        return ext_urls.stream().toArray(String[]::new);
    }
}