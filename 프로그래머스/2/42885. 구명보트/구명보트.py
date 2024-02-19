# 프로그래머스 42885 구명보트 LV.2
from collections import deque


def solution(people, limit):
    answer = 0
    boats = deque()
    people.sort()
    for i in range(len(people)):
        boats.append(people[i])

    front = 0

    while len(boats) > 1:
        if boats[front] + boats[-1] > limit:
            answer += 1
            boats.pop()
        else:
            boats.pop()
            boats.popleft()
            answer += 1

    if len(boats) == 1:
        boats.popleft()
        answer += 1
    return answer