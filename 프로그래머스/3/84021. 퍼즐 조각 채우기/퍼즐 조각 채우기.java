import java.util.*;

class Solution {
    int[] dy = new int[]{-1, 0, 1, 0}, dx = new int[]{0, 1, 0, -1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        // 1. 블록 좌표 찾기 -> 블록 상대좌표로 만들어서 저장하기
        List<List<int[]>> blocks = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == 1) {
                    List<int[]> block = new ArrayList<>();

                    bfs(table, block, new int[]{i, j}, 1, 0);

                    // 상대 좌표로 저장
                    convertRelativeCoord(block);

                    // 블록 저장
                    blocks.add(block);
                }
            }
        }

        // 2. 빈칸 찾기 -> 빈칸 찾아서 블록의 상대좌표랑 일치하는지 확인 -> 일치하면 블록 제거하고 답 기록
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board.length; j++) {
                if (game_board[i][j] == 0) {
                    List<int[]> blank = new ArrayList<>();

                    bfs(game_board, blank, new int[]{i, j}, 0, 1);

                    convertRelativeCoord(blank);

                    for (int b = 0; b < blocks.size(); b++) {
                        List<int[]> currentBlock = blocks.get(b);
                        boolean flag = false;

                        for (int k = 0; k < 4; k++) {
                            for (int[] cb : currentBlock) {
                                int tmp = cb[0];
                                cb[0] = cb[1];
                                cb[1] = -tmp;
                            }

                            convertRelativeCoord(currentBlock);

                            if (matchesBlankAndBlock(blank, currentBlock)) {
                                blocks.remove(b);
                                answer += currentBlock.size();
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public void bfs(int[][] matrix, List<int[]> result, int[] coord, int original, int changed) {
        Queue<int[]> q = new LinkedList<>();
        int y = coord[0], x = coord[1];
        q.offer(coord);
        matrix[y][x] = changed;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cy = current[0], cx = current[1];

            result.add(new int[]{cy, cx});

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i], nx = cx + dx[i];

                // 범위 안에 존재하고
                if (0 <= ny && ny < matrix.length && 0 <= nx && nx < matrix.length) {
                    // 찾은 좌표의 값이 original 이라면
                    if (matrix[ny][nx] == original) {
                        q.offer(new int[]{ny, nx});
                        matrix[ny][nx] = changed;
                    }
                }
            }
        }
    }

    public void convertRelativeCoord(List<int[]> coords) {
        coords.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int y0 = coords.get(0)[0], x0 = coords.get(0)[1];

        for (int[] c : coords) {
            c[0] -= y0;
            c[1] -= x0;
        }
    }

    public boolean matchesBlankAndBlock(List<int[]> blank, List<int[]> block) {
        if (blank.size() != block.size()) {
            return false;
        }

        for (int i = 0; i < blank.size(); i++) {
            if (!Arrays.equals(blank.get(i), block.get(i))) {
                return false;
            }
        }

        return true;
    }
}