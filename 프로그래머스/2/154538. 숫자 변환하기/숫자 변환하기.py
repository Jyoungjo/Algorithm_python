from collections import deque


def solution(x, y, n):
    visited = [0 for _ in range(y + 1)]
    q = deque()
    q.append([y, 0])
    visited[y] = 1
    while q:
        t, c = q.popleft()
        if t == x:
            return c
        for i in range(3):
            if i == 0:
                nxt = t - n
            elif i == 1:
                nxt = t // 2 if t % 2 == 0 else 0
            else:
                nxt = t // 3 if t % 3 == 0 else 0

            if nxt > 0 and not visited[nxt]:
                visited[nxt] = 1
                q.append([nxt, c + 1])
    return -1