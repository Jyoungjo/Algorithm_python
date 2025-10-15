import java.util.*;

class Solution {
    final String RT = "Rotate", SR = "ShiftRow";
    
    int R, C;
    Deque<Integer> left = new ArrayDeque<>();
    Deque<Deque<Integer>> mids = new ArrayDeque<>();
    Deque<Integer> right = new ArrayDeque<>();
    
    public int[][] solution(int[][] rc, String[] operations) {
        init(rc);
        execute(operations);
        return getResult(rc);
    }
    
    private int[][] getResult(int[][] rc) {
        for (int i = 0; i < R; i++) {
            rc[i][0] = left.removeFirst();
            rc[i][C - 1] = right.removeFirst();
            Deque<Integer> mid = mids.removeFirst();
            for (int j = 1; j < C - 1; j++) rc[i][j] = mid.removeFirst();
        }
        
        return rc;
    }
    
    private void execute(String[] operations) {
        for (String op : operations) {
            if (op.equals(RT)) {
                if (C == 2) rotate(false);
                else rotate(true);
            } else shift();
        }
    }
    
    private void rotate(boolean hasMid) {
        if (hasMid) {
            Deque<Integer> top = mids.peekFirst(), bottom = mids.peekLast();
            
            top.addFirst(left.removeFirst());
            right.addFirst(top.removeLast());
            bottom.addLast(right.removeLast());
            left.addLast(bottom.removeFirst());
        } else {
            right.addFirst(left.removeFirst());
            left.addLast(right.removeLast());
        }
    }
    
    private void shift() {
        left.addFirst(left.removeLast());
        right.addFirst(right.removeLast());
        mids.addFirst(mids.removeLast());
    }
    
    private void init(int[][] rc) {
        R = rc.length; C = rc[0].length;
        for (int i = 0; i < R; i++) {
            left.addLast(rc[i][0]);
            right.addLast(rc[i][C - 1]);
            Deque<Integer> mid = new ArrayDeque<>();
            for (int j = 1; j < C - 1; j++) mid.addLast(rc[i][j]);
            mids.addLast(mid);
        }
    }
}