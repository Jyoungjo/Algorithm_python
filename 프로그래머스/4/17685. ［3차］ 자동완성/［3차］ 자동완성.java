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
            
            node.child.putIfAbsent(c, new Node());
            node = node.child.get(c);
            node.cnt++;
        }
    }
    
    int search(String word) {
        Node node = this.root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            node = node.child.get(c);
            if (node.cnt == 1) return i + 1;
        }
        
        return word.length();
    }
}

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for (String word : words) trie.insert(word);
        for (String word : words) answer += trie.search(word);
        return answer;
    }
}