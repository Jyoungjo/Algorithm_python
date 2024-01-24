# 프로그래머스 42586 기능 개발 LV.2
def solution(progresses, speeds):
    from math import ceil
    answer = []
    days = [ceil((100 - p) / s) for p, s in zip(progresses, speeds)]
    max_day = 0
    for d in days:
        if len(answer) == 0 or max_day < d:
            max_day = d
            answer.append(1)
        else:
            answer[-1] += 1

    return answer