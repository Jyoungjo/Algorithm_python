# 프로그래머스 68645 삼각 달팽이


def solution(n):
    answer = []
    tri = [[0] * i for i in range(1, n + 1)]
    x, y = 0, 0
    t, now, end = 0, 1, sum(k for k in range(1, n + 1))
    direction = [(1, 0), (0, 1), (-1, -1)]

    while now <= end:
        tri[x][y] = now
        now += 1

        dx, dy = direction[t]
        nx, ny = x + dx, y + dy

        if 0 <= nx < n and 0 <= ny < n and tri[nx][ny] == 0:
            x, y = nx, ny
        else:
            t = (t + 1) % 3
            dx, dy = direction[t]
            x += dx
            y += dy

    for a in tri:
        for b in a:
            answer.append(b)

    return answer