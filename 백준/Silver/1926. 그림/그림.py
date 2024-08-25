import sys
from collections import deque

input = sys.stdin.readline
n, m = map(int, input().split())
painting = [list(map(int, input().split())) for _ in range(n)]
dy, dx = (-1, 0, 1, 0), (0, 1, 0, -1)
cnt, max_area = 0, float('-inf')


def bfs(start):
    global cnt, max_area
    area = 0
    q = deque()
    q.append(start)
    painting[start[0]][start[1]] = 0
    
    while q:
        y, x = q.popleft()
        area += 1

        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < n and 0 <= nx < m and painting[ny][nx] == 1:
                q.append((ny, nx))
                painting[ny][nx] = 0

    cnt += 1
    max_area = max(area, max_area)


def solution():
    global cnt, max_area
    for i in range(n):
        for j in range(m):
            if painting[i][j] == 1:
                bfs((i, j))
    if max_area == float('-inf'):
        max_area = 0
    return print(cnt, max_area, sep='\n')
            

solution()