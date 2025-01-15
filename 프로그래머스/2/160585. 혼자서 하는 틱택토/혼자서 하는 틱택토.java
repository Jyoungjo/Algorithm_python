class Solution {
    public int solution(String[] board) {
        // 규칙 어기는 실수 판단
        // 1. 승리했는데도 그 게임을 진행한 경우
        // 2. 표시 반대로 진행한 경우 (O -> X, X -> O)
        
        // O와 X의 개수를 우선 세고 이후 상황 판단
        int oCnt = 0, xCnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'O') oCnt++;
                if (board[i].charAt(j) == 'X') xCnt++;
            }
        }
        
        // 만약 한개도 없다면 아직 게임을 실행하지 않은 것이므로 가능한 상황임
        if (oCnt == 0 && xCnt == 0) return 1;
        
        // 게임 상 O의 개수가 하나 더 많거나 아니면 동일해야한다.
        // 개수를 봤을 때, X의 개수가 더 많다면 규칙 어긴 것
        if (oCnt < xCnt) return 0;
        
        if (!(oCnt == xCnt || oCnt == xCnt + 1)) return 0;
        
        // 승리하려면 O의 개수와 X의 개수가 동일한 상태에서 X가 승리 or O의 개수가 X의 개수보다 1개 많은 상태에서 O가 승리해야함
        // O의 개수가 3개 이상이 되면 승리 여부를 판단해야함
        if (oCnt >= 3) {
            // 이 때, O의 개수가 3개인데 X의 개수가 3개라면 O나 X가 승리했는지 확인.
            boolean isOWin = findWinner(board, 'O');
            boolean isXWin = findWinner(board, 'X');
            
            if (oCnt == xCnt) {
                if (!isOWin && isXWin) return 1;
                else if (!isOWin && !isXWin) return 1;
                else return 0;
            } else if (oCnt == xCnt + 1) {
                if (isOWin && !isXWin) return 1;
                else if (!isOWin && !isXWin) return 1;
                else return 0;
            } else return 0;
        }
        
        return 1;
    }
    
    private boolean findWinner(String[] board, char mark) {
        // 가로, 세로 판단
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == mark) {
                    if (board[i].charAt((j + 1) % 3) == mark && board[i].charAt((j + 2) % 3) == mark) return true;
                    if (board[(i + 1) % 3].charAt(j) == mark && board[(i + 2) % 3].charAt(j) == mark) return true;
                }
            }
        }
        // 대각 판단
        if (board[0].charAt(0) == mark && board[1].charAt(1) == mark && board[2].charAt(2) == mark) return true;
        if (board[0].charAt(2) == mark && board[1].charAt(1) == mark && board[2].charAt(0) == mark) return true;
        
        return false;
    }
}