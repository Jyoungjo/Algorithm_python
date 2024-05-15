from collections import deque


def bfs(i, j, place):
    dx, dy = (-1, 0, 1, 0), (0, 1, 0, -1)
    q = deque()
    visited = [[0 for _ in range(5)] for _ in range(5)]
    q.append([i, j, 0])
    visited[i][j] = 1
    while q:
        x, y, dist = q.popleft()
        next_dist = dist + 1
        if next_dist >= 3:
            break
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < 5 and 0 <= ny < 5 and visited[nx][ny] == 0:
                visited[nx][ny] = 1
                if place[nx][ny] == 'P' and dist <= 2:
                    return False
                if place[nx][ny] == 'O' and next_dist == 1:
                    q.append([nx, ny, dist + 1])
    return True


def solution(places):
    answer = []
    for place in places:
        flag = 1
        for i in range(5):
            for j in range(5):
                if place[i][j] == 'P':
                    if not bfs(i, j, place):
                        flag = 0
                        break
            if not flag:
                break
        answer.append(flag)
    return answer