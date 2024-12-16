import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Set<Character> winner;
    static String VALID = "valid";
    static String INVALID = "invalid";

    public static String solution(String board) {
        /*
            1. 말이 가득 찼는지 확인
            1-1. 가득 찼다면 각각의 말 개수 확인 -> X 5개 O 4개
            1-2. 가득 차면 무조건 누구 하나 승리해야하니까 1-1 맞았으면 승리 조건 확인 X

            2. 가득 안 찼다면 턴에 맞게 말이 놓아졌는지 체크
            2-1. 턴에 맞게 놓아졌다면 승리 조건 체크
                -> X 승리시 O 개수 하나 적은지 / O 승리시 X 개수 하나 적은지
         */

        int xCnt = 0, oCnt = 0;
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == 'X') xCnt++;
            if (board.charAt(i) == 'O') oCnt++;
        }

        winner = new HashSet<>();
        findWinner(board);

        // 가득 찼음
        if (xCnt + oCnt == 9 && xCnt == oCnt + 1) {
            // X 이기거나 무승부
            if (winner.isEmpty()) return VALID;
            if (winner.size() == 1 && winner.contains('X')) return VALID;
        }
        // 가득 안 찼음
        else {
            // 한명의 승자만 나와야 함
            if (winner.size() == 1) {
                // X 이김
                if (xCnt == oCnt + 1 && winner.contains('X')) return VALID;
                // O 이김
                if (xCnt == oCnt && winner.contains('O')) return VALID;
            }
        }

        return INVALID;
    }

    private static void findWinner(String board) {
        checkWidth(board);
        checkHeight(board);
        checkDiagonal(board);
    }

    private static void checkWidth(String board) {
        for (int i = 0; i < board.length(); i += 3) {
            if (findEqual(board, i, 1, 2)) winner.add(board.charAt(i));
        }
    }

    private static void checkHeight(String board) {
        for (int i = 0; i < 3; i++) {
            if (findEqual(board, i, 3, 6)) winner.add(board.charAt(i));
        }
    }

    private static void checkDiagonal(String board) {
        if (findEqual(board, 0, 4, 8)) winner.add(board.charAt(0));
        if (findEqual(board, 2, 2, 4)) winner.add(board.charAt(2));
    }

    private static boolean isEqual(String board, int a, int b) {
        return board.charAt(a) == board.charAt(b);
    }

    private static boolean findEqual(String board, int i, int n1, int n2) {
        return board.charAt(i) != '.' &&
                isEqual(board, i, i + n1) && isEqual(board, i + n1, i + n2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String board = br.readLine();
            if (board.equals("end")) {
                break;
            }

            sb.append(solution(board)).append("\n");
        }

        System.out.print(sb.toString());
    }
}