# 백준 2573 빙산 - 골드 4

# 빙산은 바닷물이 많이 접할수록 더 빨리 줄어든다 -> 빙산 탐색하면서 바닷물 접한거에 따라 높이 조절
# 빙산 두 덩어리 이상으로 분리되는 최초의 시간 구하기 -> 빙산 구역 탐색
import sys
from collections import deque
input = sys.stdin.readline

dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]

N, M = map(int, input().split())
iceberg = [list(map(int, input().split())) for _ in range(N)]


def four_way_search(y, x, melting):
    cnt = 0
    for d in range(4):
        ny, nx = y + dy[d], x + dx[d]
        if not melting[ny][nx] and iceberg[ny][nx] == 0:
            cnt += 1
    return cnt


def melt_an_iceberg():
    flag = False
    melting = [[False] * M for _ in range(N)]

    for i in range(1, N - 1):
        for j in range(1, M - 1):
            if iceberg[i][j] > 0:
                melting[i][j], flag = True, True
                cnt = four_way_search(i, j, melting)
                if iceberg[i][j] > cnt:
                    iceberg[i][j] -= cnt
                else:
                    iceberg[i][j] = 0
    return flag


def bfs(visited, start):
    q = deque([start])
    visited[start[0]][start[1]] = True
    while q:
        y, x = q.popleft()
        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < N and 0 <= nx < M and iceberg[ny][nx] > 0 and not visited[ny][nx]:
                q.append((ny, nx))
                visited[ny][nx] = True


def count_iceberg_area():
    visited = [[False] * M for _ in range(N)]
    cnt = 0
    for i in range(N):
        for j in range(M):
            if iceberg[i][j] > 0 and not visited[i][j]:
                bfs(visited, (i, j))
                cnt += 1
    return cnt


def count_years():
    year, cnt = 0, 0
    flag = True
    while flag:
        cnt = count_iceberg_area()
        if cnt >= 2:
            return year

        if not melt_an_iceberg():
            flag = False
        year += 1
    return 0


def main():
    return print(count_years())


main()