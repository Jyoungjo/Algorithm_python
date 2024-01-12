# 프로그래머스 147355 크기가 작은 부분문자열 LV.1
def solution(t, p):
    answer = 0
    idx = len(t) - len(p) + 1

    for i in range(idx):
        target = t[i:len(p) + i]
        if target <= p:
            answer += 1

    return answer
