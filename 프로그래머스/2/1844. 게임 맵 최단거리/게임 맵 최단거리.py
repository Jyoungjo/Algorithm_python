# 프로그래머스 1844 게임 맵 최단거리 LV.2
from collections import deque


def solution(maps):
    answer = -1
    visited = [[0 for _ in range(len(maps[-1]))] for _ in range(len(maps))]
    op = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    visited[0][0] = 1

    def bfs(start):
        q = deque()
        q.append(start)
        while q:
            x, y = q.popleft()
            for i in range(4):
                nx, ny = x + op[i][0], y + op[i][1]
                if 0 <= nx < len(maps) and 0 <= ny < len(maps[-1]):
                    if visited[nx][ny] == 0 and maps[nx][ny] != 0:
                        q.append((nx, ny))
                        visited[nx][ny] = visited[x][y] + 1

    bfs((0, 0))

    if visited[-1][-1] == 0:
        return answer

    return visited[-1][-1]