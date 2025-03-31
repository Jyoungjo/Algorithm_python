import java.util.*;

class Node {
    int x, y, idx;
    Node left, right;
    
    public Node(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
    
    public void addNode(Node child) {
        if (this.x > child.x) { // 왼쪽 자식
            if (this.left == null) this.left = child;
            else this.left.addNode(child);
        } else { // 오른쪽 자식
            if (this.right == null) this.right = child;
            else this.right.addNode(child);
        }
    }
}

class Solution {
    int[][] answer;
    int idx = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            int[] co = nodeinfo[i];
            nodes[i] = new Node(co[0], co[1], i + 1);
        }
        
        Arrays.sort(nodes, (o1, o2) -> {
            if (o2.y == o1.y) return o1.x - o2.x;
            return o2.y - o1.y;
        });
        
        Node root = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            root.addNode(nodes[i]);
        }
        
        answer = new int[2][nodes.length];
        preOrder(root);
        idx = 0;
        postOrder(root);
        return answer;
    }
    
    private void preOrder(Node node) {
        answer[0][idx++] = node.idx;
        if (node.left != null) preOrder(node.left);
        if (node.right != null) preOrder(node.right);
    }
    
    private void postOrder(Node node) {
        if (node.left != null) postOrder(node.left);
        if (node.right != null) postOrder(node.right);
        answer[1][idx++] = node.idx;
    }
}