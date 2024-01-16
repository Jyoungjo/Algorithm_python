# 프로그래머스 181188 요격 시스템 LV.2
def solution(targets):
    answer = 0
    targets.sort(key=lambda x: [x[1], x[0]])

    e = 0
    for target in targets:
        if target[0] >= e:
            e = target[1]
            answer += 1

    return answer