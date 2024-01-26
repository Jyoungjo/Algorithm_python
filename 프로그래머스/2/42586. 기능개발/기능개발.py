# 프로그래머스 42586 기능 개발 LV.2
def solution(progresses, speeds):
    from math import ceil
    answer = []
    days = [ceil((100 - p) / q) for p, q in zip(progresses, speeds)]
    stack = []
    for day in days:
        if len(answer) == 0 or stack[-1] < day:
            answer.append(1)
            stack.append(day)
        else:
            answer[-1] += 1
    return answer