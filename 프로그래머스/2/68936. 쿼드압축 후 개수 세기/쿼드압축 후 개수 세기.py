# 프로그래머스 68936 쿼드압축 후 개수 세기 LV.2


def solution(arr):
    answer = [0, 0]
    def dfs(r, c, size):
        v = arr[r][c]
        flag = True
        for i in range(r, r + size):
            for j in range(c, c + size):
                if arr[i][j] != v:
                    flag = False
                    break
            if not flag:
                break
        if flag:
            answer[v] += 1
        else:
            size //= 2
            dfs(r, c, size)
            dfs(r+size, c, size)
            dfs(r, c+size, size)
            dfs(r+size, c+size, size)
    dfs(0, 0, len(arr))
    return answer