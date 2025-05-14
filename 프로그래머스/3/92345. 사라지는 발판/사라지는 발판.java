/*
    A와 B가 번갈아가면서 게임 진행
    A가 진행한 방향에 따라 B가 선택할 것이 달라짐
    보드의 최대 범위가 5x5 이므로 완전 탐색 진행
    백트래킹으로 상태 가지치기 진행 -> ex) A가 이동하고 나서의 B의 이동을 정하기 때문에
    
    게임 종료 조건
    1. 한 플레이어가 이동 불가한 경우(주위 발판 X or 보드 밖)
    2. 두 플레이어가 같은 발판 위에 있을 때, 어떤 플레이어가 움직여서 발판이 사라진 경우
*/

import java.util.*;

class Game {
    boolean isWin;
    int moveCnt;
    
    Game(boolean isWin, int moveCnt) {
        this.isWin = isWin;
        this.moveCnt = moveCnt;
    }
}

class Solution {
    static final int INF = Integer.MAX_VALUE;
    final int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int[][] BOARD;
    int Y, X;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Y = board.length; X = board[0].length;
        BOARD = board;
        Game game = play(aloc, bloc, true, 0);
        return game.moveCnt;
    }
    
    private Game play(int[] aloc, int[] bloc, boolean isMyTurn, int mv) {
        int ay = aloc[0], ax = aloc[1], by = bloc[0], bx = bloc[1];
        
        if ((BOARD[ay][ax] == 0 && isMyTurn) || (BOARD[by][bx] == 0 && !isMyTurn))
            return new Game(false, mv);
        
        int y = isMyTurn ? ay : by, x = isMyTurn ? ax : bx;
        Game game = null;
        boolean canMove = false;
        int win = INF, lose = -INF;
        
        BOARD[y][x] = 0;
        
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            
            if (!isInRange(ny, nx) || BOARD[ny][nx] == 0) continue;
            
            canMove = true;
            game = isMyTurn ? play(new int[]{ny, nx}, bloc, !isMyTurn, mv + 1) 
                : play(aloc, new int[]{ny, nx}, !isMyTurn, mv + 1);
            
            if (game.isWin) lose = Math.max(lose, game.moveCnt);
            else win = Math.min(win, game.moveCnt);
        }
        
        BOARD[y][x] = 1;
        
        if (!canMove) return new Game(false, mv);
        if (win != INF) return new Game(true, win);
        return new Game(false, lose);
    }
    
    private boolean isInRange(int y, int x) {
        return 0 <= y && y < Y && 0 <= x && x < X;
    }
}