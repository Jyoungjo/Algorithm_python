import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Node {
    public final int idx;
    private final List<Node> children = new ArrayList<>();
    private int childIdx = 0;
    
    Node(int idx) { this.idx = idx; }
    
    Node trigger() {
        if (isLeaf()) return this;
        
        Node leaf = children.get(childIdx).trigger();
        childIdx = (childIdx + 1) % children.size();
        return leaf;
    }
    
    void addChild(Node child) { children.add(child); }
    void sortChild() { children.sort(Comparator.comparingInt(n -> n.idx)); }
    private boolean isLeaf() { return children.isEmpty(); }
}

class Target {
    public final int value;
    private int tries = 0;
    
    Target(int value) { this.value = value; }
    
    void addTry() { tries++; }
    boolean isNotEnough() { return value > tries * 3; }
    boolean didTooManyTries() { return value < tries; }
    boolean isSolved() { return !isNotEnough() && !didTooManyTries(); }
    Queue<Integer> solve() {
        int[] count = new int[3];
        int remainders = value - tries;
        count[2] = remainders / 2;
        count[1] = remainders % 2;
        count[0] = tries - count[1] - count[2];
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) q.add(i + 1);
        }
        
        return q;
    }
}

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        Node tree = constructTree(edges);
        
        List<Integer> leaves = new ArrayList<>();
        Target[] targets = Arrays.stream(target).mapToObj(Target::new).toArray(Target[]::new);
        do {
            int leaf = tree.trigger().idx;
            leaves.add(leaf);
            targets[leaf].addTry();
            
            if (checkAll(targets, Target::didTooManyTries)) return new int[]{-1};
        } while (!checkAll(targets, Target::isSolved));
        
        List<Queue<Integer>> queues = Arrays.stream(targets).map(Target::solve).collect(Collectors.toList());
        return leaves.stream().mapToInt(leaf -> queues.get(leaf).poll()).toArray();
    }
    
    private Node constructTree(int[][] edges) {
        Node[] nodes = new Node[edges.length + 1];
        for (int i = 0; i < nodes.length; i++) nodes[i] = new Node(i);
        for (int[] edge : edges) nodes[edge[0] - 1].addChild(nodes[edge[1] - 1]);
        for (Node node : nodes) {
            if (node == null) continue;
            node.sortChild();
        }
        
        return nodes[0];
    }
    
    private boolean checkAll(Target[] targets, Function<Target, Boolean> map) {
        for (Target t : targets) {
            if (t.value == 0) continue;
            if (!map.apply(t)) return false;
        }
        
        return true;
    }
}