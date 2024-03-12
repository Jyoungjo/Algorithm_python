# 프로그래머스 42898 등굣길 LV.3
def solution(m, n, puddles):
    map = [[1 for _ in range(m)] for _ in range(n)]
    denominator = 1000000007

    for x, y in puddles:
        if x == 1:
            for i in range(y - 1, n):
                map[i][x - 1] = 0
        if y == 1:
            for i in range(x - 1, m):
                map[y - 1][i] = 0
        map[y - 1][x - 1] = 0

    for x in range(m):
        for y in range(n):
            if x - 1 < 0 or y - 1 < 0 or map[y][x] == 0:
                continue
            map[y][x] = map[y][x - 1] + map[y - 1][x]

    return map[n - 1][m - 1] % denominator