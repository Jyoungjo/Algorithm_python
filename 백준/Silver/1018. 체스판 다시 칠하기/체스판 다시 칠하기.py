# 백준 1018 체스판 다시 칠하기 - 실버 4
# M*N 체스판 -> 8*8 체스판 만들기
# 무조건 번갈아서 칠해져야함.
# 8*8로 잘라낸 후에 잘못된 부분 다시 칠함 -> 다시 칠해야 하는 최소 개수 구하기
import sys


def count_wrong_point(x, y):
    current_color = grid[x][y]
    cnt = 0
    for i in range(x, x+8):
        for j in range(y, y+8):
            if grid[i][j] != current_color:
                cnt += 1
            current_color = 'W' if current_color == 'B' else 'B'
        current_color = 'W' if current_color == 'B' else 'B'
    return min(cnt, 64 - cnt) # 시작 색깔로 계속 가는 경우 vs 시작 색깔을 바꿔서 하는 경우


N, M = map(int, sys.stdin.readline().strip().split())
grid = [[x for x in sys.stdin.readline().strip()] for _ in range(N)]
answer = float('inf')
for i in range(N-7):
    for j in range(M-7):
        answer = min(answer, count_wrong_point(i, j))
print(answer)