import java.util.*;

class Node {
    Map<Character, Node> child = new HashMap<>();
    int pass, end;
    
    Node() { this.pass = 0; this.end = 0; }
}

class Trie {
    final char WILD_CARD = '?';
    Node root;
    
    Trie() { this.root = new Node(); }
    
    void insert(String word) {
        Node node = this.root;
        node.pass++;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node.child.putIfAbsent(c, new Node());
            node = node.child.get(c);
            node.pass++;
        }
        
        node.end++;
    }
    
    int search(String query) {
        Node node = this.root;
        
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (c == WILD_CARD) return node.pass;
            
            node = node.child.get(c);
            if (node == null) return 0;
        }
        
        return node.end;
    }
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
        /*
            ? 가 포함되었다면 전부 매칭 가능
            words 주고 찾는 키워드 담긴 배열 줄 때 키워드 매칭 단어 몇 개인지 순서대로
        */
        
        int[] answer = new int[queries.length];
        Map<Integer, Trie> forward = new HashMap<>();
        Map<Integer, Trie> backward = new HashMap<>();
        
        for (String word : words) {
            int len = word.length();
            forward.putIfAbsent(len, new Trie());
            forward.get(len).insert(word);
            
            String reversed = new StringBuilder(word).reverse().toString();
            backward.putIfAbsent(len, new Trie());
            backward.get(len).insert(reversed);
        }
        
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int len = query.length();
            
            if (!forward.containsKey(len)) {
                answer[i] = 0;
                continue;
            }
            
            if (query.charAt(0) == '?') {
                String rQuery = new StringBuilder(query).reverse().toString();
                answer[i] = backward.get(len).search(rQuery);
            } else if (query.charAt(len - 1) == '?') {
                answer[i] = forward.get(len).search(query);
            }
        }
        
        return answer;
    }
}