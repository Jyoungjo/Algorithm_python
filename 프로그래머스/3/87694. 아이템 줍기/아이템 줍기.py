# 프로그래머스 87694 아이템 줍기 LV.3
from collections import deque


def solution(rectangle, characterX, characterY, itemX, itemY):
    field = [[-1] * 102 for _ in range(102)]

    for r in rectangle:
        x1, y1, x2, y2 = map(lambda x: x * 2, r)
        for i in range(x1, x2 + 1):
            for j in range(y1, y2 + 1):
                if x1 < i < x2 and y1 < j < y2:
                    field[i][j] = 0
                elif field[i][j] != 0:
                    field[i][j] = 1

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    visited = [[False] * 102 for _ in range(102)]
    visited[characterX * 2][characterY * 2] = True

    def bfs(x, y):
        q = deque()
        q.append([x, y, 0])
        while q:
            x, y, step = q.popleft()

            if x == itemX * 2 and y == itemY * 2:
                return step // 2

            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if field[nx][ny] == -1:
                    continue
                if not visited[nx][ny] and field[nx][ny] == 1:
                    visited[nx][ny] = True
                    q.append([nx, ny, step + 1])

    return bfs(characterX * 2, characterY * 2)