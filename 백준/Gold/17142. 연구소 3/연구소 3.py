# 백준 17142 연구소 3 - 골드 3

# 바이러스 몇 초만에 퍼지는지 최소 시간 구하기
# 바이러스 위치 확인 후 좌표 저장 -> 좌표를 조합으로 경우의 수 만들기 -> bfs 돌리기
import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

N, M = map(int, input().split())
field = [list(map(int, input().split())) for _ in range(N)]
dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
blank = 0
INF = float('inf')


def find_virus():
    global blank
    v_list = []
    for i in range(N):
        for j in range(N):
            if field[i][j] == 2:
                v_list.append((i, j))
            if field[i][j] == 0:
                blank += 1
    return v_list


def bfs(virus, b):
    q = deque()
    visited = [[False] * N for _ in range(N)]
    time = 0

    for i, j in virus:
        q.append((i, j, 0))
        visited[i][j] = True

    while q:
        y, x, c = q.popleft()
        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < N and 0 <= nx < N:
                if field[ny][nx] == 0 and not visited[ny][nx]:
                    q.append((ny, nx, c+1))
                    visited[ny][nx] = True
                    b -= 1
                    time = max(time, c+1)
                if field[ny][nx] == 2 and not visited[ny][nx]:
                    q.append((ny, nx, c+1))
                    visited[ny][nx] = True

    return INF if b != 0 else time


def solution():
    result = INF
    for virus in combinations(find_virus(), M):
        b = blank
        result = min(result, bfs(virus, b))
    return result if result != INF else -1


def main():
    return print(solution())


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()