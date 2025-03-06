import java.util.*;

class Solution {
    int R, C;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        R = rows;
        C = columns;
        int[][] matrix = makeMatrix(rows, columns);
        for (int i = 0; i < queries.length; i++) {
            int r1 = queries[i][0], c1 = queries[i][1], r2 = queries[i][2], c2 = queries[i][3];
            answer[i] = rotate(matrix, r1 - 1, c1 - 1, r2 - 1, c2 - 1);
        }

        return answer;
    }
    
    private int rotate(int[][] matrix, int r1, int c1, int r2, int c2) {
        int min = Integer.MAX_VALUE;
        int tmp = matrix[r1][c2];
        // 위쪽 회전
        for (int j = c2; j > c1; j--) {
            min = Math.min(min, matrix[r1][j]);
            matrix[r1][j] = matrix[r1][j - 1];
        }
        // 왼쪽 회전
        for (int i = r1; i < r2; i++) {
            min = Math.min(min, matrix[i][c1]);
            matrix[i][c1] = matrix[i + 1][c1];
        }
        // 아래쪽 회전
        for (int j = c1; j < c2; j++) {
            min = Math.min(min, matrix[r2][j]);
            matrix[r2][j] = matrix[r2][j + 1];
        }
        // 오른쪽 회전
        for (int i = r2; i > r1; i--) {
            min = Math.min(min, matrix[i][c2]);
            matrix[i][c2] = matrix[i - 1][c2];
        }
        matrix[r1 + 1][c2] = tmp;
        return min;
    }
    
    private int[][] makeMatrix(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= columns; j++) {
                arr[i][j - 1] = columns * i + j;
            }
        }
        return arr;
    }
}