# 백준 10026 적록색약 - 골드 5

# bfs -> 각 구역에 따른 사방탐색 진행
# 먼저 기존 grid 구하고 그 다음에 적록색맹 구한다.
import sys
from collections import deque

input = sys.stdin.readline

dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]


def bfs(N, grid):
    res = 0
    visited = [[False] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                q = deque([(i, j)])
                visited[i][j] = True
                while q:
                    x, y = q.popleft()
                    for d in range(4):
                        nx, ny = x + dx[d], y + dy[d]
                        if (0 <= nx < len(grid)
                                and 0 <= ny < len(grid[0])
                                and grid[x][y] == grid[nx][ny]
                                and not visited[nx][ny]):
                            q.append((nx, ny))
                            visited[nx][ny] = True
                res += 1
    return res


def change_val(N, grid):
    for i in range(N):
        for j in range(N):
            if grid[i][j] == 'G':
                grid[i][j] = 'R'


def main():
    N = int(input())
    grid = [[x for x in input().strip()] for _ in range(N)]
    original = bfs(N, grid)
    change_val(N, grid)
    color_blindness = bfs(N, grid)
    return print(original, color_blindness)


main()