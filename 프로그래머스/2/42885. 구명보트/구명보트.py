# 프로그래머스 42885 구명보트 LV.2
from collections import deque


def solution(people, limit):
    answer = 0
    boats = deque(sorted(people))

    while boats:
        first = boats.popleft()
        if not boats:
            return answer + 1
        last = boats.pop()

        if first + last > limit:
            boats.appendleft(first)
        answer += 1

    return answer