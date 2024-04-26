from collections import deque


def solution(board):
    answer = -1
    dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]
    r, c = len(board), len(board[0])
    start = [-1, -1]
    visited = [[0 for _ in range(c)] for _ in range(r)]

    for i in range(r):
        for j in range(c):
            if board[i][j] == 'R':
                start = [i, j]
                break
        if start[0] != -1: break

    def move(x, y, d):
        while True:
            x += dx[d]
            y += dy[d]
            if x < 0 or x >= r or y < 0 or y >= c or board[x][y] == 'D':
                break
        x -= dx[d]
        y -= dy[d]
        return [x, y]

    q = deque()
    q.append([start[0], start[1], 0])
    while q:
        cx, cy, distance = q.popleft()
        for d in range(4):
            nx, ny = move(cx, cy, d)

            if visited[nx][ny]:
                continue
            elif board[nx][ny] == 'G':
                return distance + 1
            else:
                visited[nx][ny] = 1
                q.append([nx, ny, distance + 1])

    return answer