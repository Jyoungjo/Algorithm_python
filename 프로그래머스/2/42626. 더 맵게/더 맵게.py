# 프로그래머스 42626 더 맵게 LV.2
import heapq


def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)
    while True:
        first = heapq.heappop(scoville)
        if first >= K:
            break
        if len(scoville) == 0:
            return -1
        second = heapq.heappop(scoville)
        heapq.heappush(scoville, first + second * 2)
        answer += 1
    return answer