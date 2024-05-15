# 프로그래머스 140107 점 찍기


def solution(k, d):
    answer = 0
    for x in range(0, d + 1, k):
        y2 = d ** 2 - x ** 2
        point_cnt = y2 ** 0.5 // k + 1
        answer += point_cnt
    return answer