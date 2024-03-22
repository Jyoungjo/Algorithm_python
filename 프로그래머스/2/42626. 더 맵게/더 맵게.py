# 프로그래머스 42626 더 맵게 LV.2
import heapq


def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)
    while scoville:
        if len(scoville) == 1 and scoville[-1] < K:
            return -1
        if all(sc >= K for sc in scoville):
            return answer
        else:
            heapq.heappush(scoville, heapq.heappop(scoville) + (heapq.heappop(scoville) * 2))
            answer += 1
    return answer