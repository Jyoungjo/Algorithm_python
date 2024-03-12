# 프로그래머스 42884 단속 카메라 LV.3
def solution(routes):
    answer = 0
    routes.sort(key=lambda x: x[1])
    target = -30001

    for route in routes:
        start, end = route

        if target < start:
            target = end
            answer += 1

    return answer