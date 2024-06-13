# 백준 17086 아기 상어 2 - 실버 2

# 안전 거리 = 해당 칸에서 부터 가장 가까운 아기 상어와의 거리
# 상어를 기준으로 탐색 진행 후, 가장 최댓값을 리턴
# 상어가 있는 위치를 전부 q에 넣고 팔방탐색 진행

import sys
from collections import deque
input = sys.stdin.readline


def find_max_val(distance):
    max_val = 0
    for dist in distance:
        for d in dist:
            max_val = max(max_val, d)
    return max_val


def is_within_the_range(y, x, N, M):
    return 0 <= y < N and 0 <= x < M


def bfs(N, M, distance, q):
    dy, dx = [-1, -1, 0, 1, 1, 1, 0, -1], [0, 1, 1, 1, 0, -1, -1, -1]
    while q:
        y, x = q.popleft()
        for d in range(8):
            ny, nx = y + dy[d], x + dx[d]
            if is_within_the_range(ny, nx, N, M) and distance[ny][nx] == -1:
                distance[ny][nx] = distance[y][x] + 1
                q.append((ny, nx))


def solution(N, M, grid):
    distance = [[-1] * M for _ in range(N)]
    q = deque()
    for i in range(N):
        for j in range(M):
            if grid[i][j] == 1:
                q.append((i, j))
                distance[i][j] = 0
    bfs(N, M, distance, q)
    return find_max_val(distance)


def main():
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    return print(solution(N, M, grid))


main()