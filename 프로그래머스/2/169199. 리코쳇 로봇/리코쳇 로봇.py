from collections import deque


def bfs(r, c, board):
    dx, dy = (-1, 0, 1, 0), (0, 1, 0, -1)
    visited = [[0 for _ in range(len(board[0]))] for _ in range(len(board))]
    q = deque()
    q.append([r, c, 0])
    visited[r][c] = 1
    while q:
        x, y, dist = q.popleft()
        for d in range(4):
            def move(x, y, d):
                while 0 <= x < len(board) and 0 <= y < len(board[0]) and board[x][y] != 'D':
                    x += dx[d]
                    y += dy[d]
                x -= dx[d]
                y -= dy[d]
                return [x, y]
            nx, ny = move(x, y, d)

            if visited[nx][ny]:
                continue
            elif board[nx][ny] == 'G':
                return dist + 1
            else:
                visited[nx][ny] = 1
                q.append([nx, ny, dist + 1])
    return -1


def solution(board):
    row, col = len(board), len(board[0])
    for i in range(row):
        for j in range(col):
            if board[i][j] == 'R':
                return bfs(i, j, board)