# 프로그래머스 43165 타겟 넘버 LV.2
answer = 0
def dfs(idx, numbers, target, value):
    global answer
    l = len(numbers)
    if idx == l:
        if target == value:
            answer += 1
        return

    dfs(idx + 1, numbers, target, value + numbers[idx])
    dfs(idx + 1, numbers, target, value - numbers[idx])


def solution(numbers, target):
    dfs(0, numbers, target, 0)
    return answer