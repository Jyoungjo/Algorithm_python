# 백준 7569 토마토 - 골드 5

# 방향을 6개 지정 (전후좌우상하) -> BFS로 육방탐색 진행
import sys
from collections import deque

input = sys.stdin.readline

dx, dy, dz = [0, 0, 0, 1, 0, -1], [0, 0, -1, 0, 1, 0], [-1, 1, 0, 0, 0, 0]


def search_of_the_six_directions(tomatoes, q, cnt):
    days = 0
    while q:
        z, y, x, day = q.popleft()
        for d in range(6):
            nx, ny, nz = x + dx[d], y + dy[d], z + dz[d]
            if (0 <= nx < len(tomatoes[0][0])
                    and 0 <= ny < len(tomatoes[0])
                    and 0 <= nz < len(tomatoes)
                    and not tomatoes[nz][ny][nx]):
                q.append((nz, ny, nx, day + 1))
                tomatoes[nz][ny][nx] = 1
                cnt -= 1
                days = day + 1
                
    return -1 if cnt else days


def main():
    M, N, H = map(int, input().split())
    tomatoes = [[list(map(int, input().split())) for _ in range(N)] for _ in range(H)]
    q = deque()
    cnt = 0
    for i in range(H):
        for j in range(N):
            for k in range(M):
                if tomatoes[i][j][k] == 1:
                    q.append((i, j, k, 0))
                elif not tomatoes[i][j][k]:
                    cnt += 1
    return print(search_of_the_six_directions(tomatoes, q, cnt)) if cnt else print(0)


main()
