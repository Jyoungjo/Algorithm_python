# 프로그래머스 49191 순위 LV.3
def solution(n, results):
    answer = 0
    graph = [[0] * (n + 1) for _ in range(n + 1)]
    for a, b in results:
        graph[a][b] = 1
        graph[b][a] = -1

    for k in range(1, n + 1):
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if graph[i][j] == 0:
                    if graph[i][k] == 1 and graph[k][j] == 1:
                        graph[i][j] = 1
                    elif graph[i][k] == -1 and graph[k][j] == -1:
                        graph[i][j] = -1

    for i in range(1, n + 1):
        if graph[i].count(0) == 2:
            answer += 1

    return answer