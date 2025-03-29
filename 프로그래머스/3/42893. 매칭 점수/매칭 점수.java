import java.util.*;
import java.util.regex.*;

class Link {
    int idx;
    String url;
    String[] ext_urls;
    double score, l_score;
    
    public Link(int idx, String url) {
        this.idx = idx;
        this.url = url;
    }
    
    public void addExtUrls(List<String> extUrlList) {
        this.ext_urls = extUrlList.toArray(String[]::new);
    }
    
    public void addScore(double score) {
        this.score += score;
    }
    
    public void setLinkScore() {
        this.l_score = this.score / this.ext_urls.length;
    }
}

class Solution {
    Map<String, Link> linkMap = new HashMap<>();
    
    public int solution(String word, String[] pages) {
        /*
            1. 기본 점수 = 검색어 등장 횟수(대소문자 무시)
            2. 외부 링크수 = 다른 외부 페이지로 연결된 링크 개수
            3. 링크 점수 = 해당 웹페이지 링크가 걸린 다른 웹페이지 기본 점수 / 외부 링크 수의 총합
            4. 매칭 점수 = 기본 점수 + 링크 점수
            
            매칭 점수가 가장 높은 페이지 idx 구하기 -> 여러 개면 작은 번호
        */
        
        int answer = 21;
        
        Pattern home_url_pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Pattern url_pattern = Pattern.compile("<a href=\"(\\S*)\"");
        Pattern word_pattern = Pattern.compile("\\b(?i)" + word + "\\b");
        Matcher home_url_matcher, url_matcher, word_matcher;
        
        for (int i = 0; i < pages.length; i++) {
            home_url_matcher = home_url_pattern.matcher(pages[i]);
            url_matcher = url_pattern.matcher(pages[i]);
            
            String home_url = "";
            
            if (home_url_matcher.find()) {
                home_url = home_url_matcher.group().split("=")[2].replaceAll("\"", "");
            }
            
            Link link = new Link(i, home_url);
            List<String> ext_urls = new ArrayList<>();
            
            while (url_matcher.find()) {
                ext_urls.add(url_matcher.group().split("=")[1].replaceAll("\"", ""));
            }
            
            link.addExtUrls(ext_urls);
            
            String body = pages[i].split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
            word_matcher = word_pattern.matcher(body);
            int wordCnt = 0;
            while (word_matcher.find()) wordCnt++;
            link.addScore(wordCnt);
            link.setLinkScore();
            
            linkMap.put(home_url, link);
        }
        
        for (Link link : linkMap.values()) {
            for (String extLink : link.ext_urls) {
                if (linkMap.containsKey(extLink)) {
                    linkMap.get(extLink).addScore(link.l_score);
                }
            }
        }
        
        double max_score = -1;
        for (Link link : linkMap.values()) {
            if (max_score < link.score) {
                max_score = link.score;
                answer = link.idx;
            } else if (max_score == link.score && answer > link.idx) {
                answer = link.idx;
            }
        }
        
        return answer;
    }
}