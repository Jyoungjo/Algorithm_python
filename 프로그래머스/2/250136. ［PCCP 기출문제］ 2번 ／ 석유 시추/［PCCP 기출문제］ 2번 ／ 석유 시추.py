# 프로그래머스 250136 석유 시추 LV.2
from collections import deque


def solution(land):
    answer = 0 # 총 석유량
    n, m = len(land), len(land[0])
    dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]
    coord_and_oil = dict()
    for i in range(n):
        for j in range(m):
            # 석유가 있는 땅인 경우
            if land[i][j] == 1:
                q = deque()
                q.append([i, j])
                land[i][j] = 0
                oil = 1
                y_set = set()
                while q:
                    x, y = q.popleft()
                    y_set.add(y)
                    for k in range(4):
                        nx, ny = x + dx[k], y + dy[k]
                        if 0 <= nx < n and 0 <= ny < m and land[nx][ny] == 1:
                            q.append([nx, ny])
                            land[nx][ny] = 0
                            oil += 1

                for cy in y_set:
                    if not coord_and_oil.get(cy):
                        coord_and_oil[cy] = 0
                    coord_and_oil[cy] += oil

    return max(coord_and_oil.values())