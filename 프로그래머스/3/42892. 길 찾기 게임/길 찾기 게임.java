import java.util.*;

class Node {
    int idx, y, x;
    Node left, right;
    
    public Node(int idx, int y, int x) {
        this.idx = idx;
        this.y = y;
        this.x = x;
    }
    
    public void addNode(Node node) {
        if (this.x > node.x) {
            if (this.left == null) this.left = node;
            else this.left.addNode(node);
        } else {
            if (this.right == null) this.right = node;
            else this.right.addNode(node);
        }
    }
}

class Solution {
    int idx;
    Node[] nodes;
    int[][] answer;
    
    public int[][] solution(int[][] nodeinfo) {
        int len = nodeinfo.length;
        
        // 1. idx 담은 노드 배열 생성
        nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][1], nodeinfo[i][0]);
        }
        
        // 2. 배열 정렬 y 내림차순, x 오름차순
        Arrays.sort(nodes, (o1, o2) ->  {
            if (o1.y == o2.y) return o1.x - o2.x;
            return o2.y - o1.y;
        });
        
        // 노드 등록
        Node rootNode = null;
        for (int i = 0; i < len; i++) {
            if (rootNode == null) {
                rootNode = nodes[i];
                continue;
            }
            
            rootNode.addNode(nodes[i]);
        }
        
        // 4. 노드 순회
        answer = new int[2][len];
        idx = 0;
        preorder(answer[0], rootNode);
        idx = 0;
        postorder(answer[1], rootNode);
        
        return answer;
    }
    
    private void preorder(int[] arr, Node node) {
        arr[idx++] = node.idx;
        if (node.left != null) preorder(arr, node.left);
        if (node.right != null) preorder(arr, node.right);
    }
    
    private void postorder(int[] arr, Node node) {
        if (node.left != null) postorder(arr, node.left);
        if (node.right != null) postorder(arr, node.right);
        arr[idx++] = node.idx;
    }
}