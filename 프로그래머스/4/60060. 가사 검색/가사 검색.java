import java.util.*;

class Node {
    Map<Character, Node> child = new HashMap<>();
    int cnt;
}

class Trie {
    Node root;
    
    Trie() { this.root = new Node(); }
    
    void insert(String word) {
        Node node = this.root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node.cnt++;
            
            node.child.putIfAbsent(c, new Node());
            node = node.child.get(c);
        }
    }
    
    int search(String word) {
        Node node = this.root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '?') return node.cnt;
            
            node = node.child.get(c);
            if (node == null) return 0;
        }
        
        return node.cnt;
    }
}

class Solution {
    Map<Integer, Trie> natural = new HashMap<>();
    Map<Integer, Trie> reverse = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    
    public int[] solution(String[] words, String[] queries) {
        insertWords(words);
        return searchQueries(queries);
    }
    
    private int[] searchQueries(String[] queries) {
        int[] answer = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int len = query.length();
            
            Trie trie;
            if (query.charAt(0) == '?') {
                if (!reverse.containsKey(len)) {
                    answer[i] = 0;
                    continue;
                }
                
                trie = reverse.get(len);
                answer[i] = trie.search(sb.append(query).reverse().toString());
                sb.setLength(0);
                continue;
            }
            
            if (!natural.containsKey(len)) {
                answer[i] = 0;
                continue;
            }
            trie = natural.get(len);
            answer[i] = trie.search(query);
        }
        
        return answer;
    }
    
    private void insertWords(String[] words) {
        for (String word : words) {
            int len = word.length();
            natural.putIfAbsent(len, new Trie());
            reverse.putIfAbsent(len, new Trie());
            
            Trie nTrie = natural.get(len);
            Trie rTrie = reverse.get(len);
            
            nTrie.insert(word);
            rTrie.insert(sb.append(word).reverse().toString());
            sb.setLength(0);
        }
    }
}