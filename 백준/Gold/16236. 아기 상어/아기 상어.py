# 백준 16236 아기 상어 - 골드 3

# 더 이상 먹을 수 있는 물고기 없으면 종료
# 먹을 수 있는 물고기가 1마리 이상 -> 거리가 가장 가까운 물고기 먹는다
# 거리 가까운 물고기 많으면 가장 위에 물고기 / 그런 물고기 여러마리 -> 왼쪽 물고기
import sys
from collections import deque
input = sys.stdin.readline

dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
INF = float('inf')
N = int(input())
now_x, now_y = 0, 0


def find_edible_fish(field, min_dist_arr, shark_size):
    y, x = 0, 0
    min_val = INF
    for i in range(N):
        for j in range(N):
            if min_dist_arr[i][j] != -1 and 1 <= field[i][j] < shark_size:
                if min_dist_arr[i][j] < min_val:
                    min_val = min_dist_arr[i][j]
                    y, x = i, j
    if min_val == INF:
        return None
    else:
        return y, x, min_val


def bfs(start, field, shark_size):
    visited = [[-1] * N for _ in range(N)]
    visited[start[0]][start[1]] = 0
    q = deque([start])
    while q:
        y, x = q.popleft()
        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < N and 0 <= nx < N and shark_size >= field[ny][nx] and visited[ny][nx] == -1:
                visited[ny][nx] = visited[y][x] + 1
                q.append((ny, nx))
    return visited


def find_baby_shark(field):
    for i in range(N):
        for j in range(N):
            if field[i][j] == 9:
                field[i][j] = 0
                return i, j


def solution(field):
    ans, shark_size, food_count = 0, 2, 0
    now_y, now_x = find_baby_shark(field)
    while True:
        min_dist_arr = bfs((now_y, now_x), field, shark_size)
        result = find_edible_fish(field, min_dist_arr, shark_size)
        if not result:
            return ans
        else:
            now_y, now_x = result[0], result[1]
            ans += result[2]
            field[now_y][now_x] = 0
            food_count += 1
            if food_count >= shark_size:
                shark_size += 1
                food_count = 0


def main():
    field = [list(map(int, input().split())) for _ in range(N)]
    return print(solution(field))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()