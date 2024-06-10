# 백준 3055 탈출 - 골드 4

# 이동하지 못하는 조건
# 1. X or * 일때
# 2. 범위 밖일 때
# 3. 방문 했을 때
# 4. current = '*' 인데 D로 가려고 할 때

# bfs 진행하면서 물 -> 고슴도치 순으로 진행 (물이 간 위치는 갈 수 없음)

import sys
from collections import deque

input = sys.stdin.readline

dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]


def find_coordinates(row, col, forest, visited, q):
    for i in range(row):
        for j in range(col):
            if forest[i][j] == 'S':
                q.append((i, j))
                visited[i][j] = 0
            if forest[i][j] == '*':
                q.appendleft((i, j))


def bfs(q, forest, visited):
    while q:
        x, y = q.popleft()
        current = forest[x][y]
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]

            if (0 <= nx < len(forest)
                    and 0 <= ny < len(forest[0])
                    and forest[nx][ny] != 'X'
                    and forest[nx][ny] != '*'
                    and visited[nx][ny] == -1
                    and not (current == '*' and forest[nx][ny] == 'D')):

                if current == 'S':
                    if forest[nx][ny] == 'D':
                        return visited[x][y] + 1
                    visited[nx][ny] = visited[x][y] + 1
                forest[nx][ny] = current
                q.append((nx, ny))
    return 'KAKTUS'


def main():
    R, C = map(int, input().split())
    map_of_forest = [[x for x in input().strip()] for _ in range(R)]
    visited = [[-1] * C for _ in range(R)]
    q = deque()
    find_coordinates(R, C, map_of_forest, visited, q)
    return print(bfs(q, map_of_forest, visited))


main()
