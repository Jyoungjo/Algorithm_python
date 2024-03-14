# 프로그래머스 43164 여행 경로 LV.3
def solution(tickets):
    tickets.sort(key=lambda x: (x[0], x[1]))

    def dfs(t, path):
        if len(t) == 0:
            return path

        now = path[-1]
        idx = -1

        for i in range(len(t)):
            if now == t[i][0]:
                idx = i
                break

        if idx == -1:
            return []

        while t[idx][0] == now:
            next_path = dfs(t[:idx] + t[idx + 1:], path + [t[idx][1]])

            if next_path:
                return next_path

            idx += 1

    return dfs(tickets, ['ICN'])