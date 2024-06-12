# 백준 16918 봄버맨 - 실버 1

# 시간마다 변경되는 격자의 모양을 리턴하는 문제
# 구현해야하는 기능마다 컴포넌트로 분리했고 폭탄이 폭발할 때, 3초 이상 지난 폭탄이 같이 사라지는 경우가 있다.
# bfs로 근처 3초 이상 경과된 폭탄 찾아서 같이 터트려 준다.

import sys
from collections import deque
input = sys.stdin.readline


def init(R, C, grid):
    for i in range(R):
        for j in range(C):
            grid[i][j] = 0 if grid[i][j] == '.' else 1


def update_bombs_time(grid):
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] != 0:
                grid[i][j] += 1


def install_bombs(grid):
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] == 0:
                grid[i][j] = 1


def explode_bombs(grid):
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] >= 3:
                check_and_explode(grid, i, j)


def check_and_explode(grid, i, j):
    dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
    q = deque([(i, j)])
    grid[i][j] = 0
    while q:
        y, x = q.popleft()
        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < len(grid) and 0 <= nx < len(grid[0]):
                if grid[ny][nx] >= 3:
                    q.append((ny, nx))
                grid[ny][nx] = 0


def convert(grid, R, C):
    for i in range(R):
        for j in range(C):
            grid[i][j] = '.' if grid[i][j] == 0 else 'O'


def run(N, grid):
    cnt = 1
    # 처음 폭탄 설치하는 것 이외에는 짝수 초에는 업데이트와 설치만, 홀수 초에는 폭발까지 같이 일어남
    # 홀수, 짝수 초 -> 시간 경과 업데이트, 빈 공간 폭탄 설치
    # 홀수 초 -> 폭발
    while cnt < N:
        cnt += 1
        # 1. 0이 아닌 곳 시간 업데이트
        update_bombs_time(grid)
        # 2. 폭탄 설치
        install_bombs(grid)
        if cnt % 2 == 1:
            # 3. 폭발
            explode_bombs(grid)
        if cnt == N:
            return


def main():
    R, C, N = map(int, input().split())
    grid = [[x for x in input().strip()] for _ in range(R)]
    init(R, C, grid)
    run(N, grid)
    convert(grid, R, C)
    return print('\n'.join(''.join(map(str, g)) for g in grid))


main()
