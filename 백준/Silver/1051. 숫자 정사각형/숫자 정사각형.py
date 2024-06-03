# 백준 1051 숫자 정사각형 - 실버 3
# N*M 직사각형에서 꼭짓점이 모두 일치하는 가장 큰 정사각형 찾기
import sys


def find_square(l):
    cnt = 0
    for i in range(N - l + 1):
        for j in range(M - l + 1):
            point_num = grid[i][j]
            if grid[i][j+l-1] == point_num and grid[i+l-1][j] == point_num and grid[i+l-1][j+l-1] == point_num:
                cnt = l**2
    return cnt


N, M = map(int, sys.stdin.readline().strip().split())
grid = [[x for x in sys.stdin.readline().strip()] for _ in range(N)]
answer = float('-inf')
limit = min(N, M)
for l in range(limit, 0, -1):
    answer = max(answer, find_square(l))
print(answer)