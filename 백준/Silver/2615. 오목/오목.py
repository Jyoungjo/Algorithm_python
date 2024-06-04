# 백준 2615 오목 - 실버 1
# 2번
import sys


def play_the_game():
    for x in range(N):
        for y in range(N):
            if board[x][y] != 0:
                target = board[x][y]

                for d in range(4):
                    cnt = 1
                    nx, ny = x + dx[d], y + dy[d]

                    while 0 <= nx < N and 0 <= ny < N and board[nx][ny] == target:
                        cnt += 1
                        if cnt == 5:
                            if 0 <= x - dx[d] < N and 0 <= y - dy[d] < N and board[x - dx[d]][y - dy[d]] == target:
                                break
                            if 0 <= nx + dx[d] < N and 0 <= ny + dy[d] < N and board[nx + dx[d]][ny + dy[d]] == target:
                                break
                            return target, x+1, y+1

                        nx += dx[d]
                        ny += dy[d]
    return 0, -1, -1


input = sys.stdin.readline
N = 19
board = [list(map(int, input().split())) for _ in range(N)]
dx, dy = [0, 1, 1, -1], [1, 0, 1, 1]
color, x, y = play_the_game()
if color:
    print(color)
    print(x, y)
else:
    print(color)