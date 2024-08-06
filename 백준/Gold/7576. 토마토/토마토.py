# 백준 7576 토마토 -


import sys
from collections import deque
input = sys.stdin.readline
M, N = map(int, input().split())
box = [list(map(int, input().split())) for _ in range(N)]
dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
blank = 0


def find_tomatoes():
    global blank
    tomatoes = []
    flag = False
    for i in range(N):
        for j in range(M):
            if box[i][j] == 0:
                flag = True
                blank += 1
            if box[i][j] == 1:
                tomatoes.append((i, j))
    return tomatoes if flag else None


def is_under_the_range(y, x):
    return 0 <= y < N and 0 <= x < M


def bfs(tomatoes):
    global blank
    q = deque()
    time = 0
    for i, j in tomatoes:
        q.append((i, j, 0))
    while q:
        y, x, cnt = q.popleft()
        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if is_under_the_range(ny, nx) and not box[ny][nx]:
                q.append((ny, nx, cnt + 1))
                box[ny][nx] = 1
                blank -= 1
                time = max(time, cnt + 1)
    return time if not blank else -1


def solution():
    tomatoes = find_tomatoes()
    return 0 if tomatoes is None else bfs(tomatoes)


def main():
    return print(solution())


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()