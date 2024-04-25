# 프로그래머스 214288 상담원 인원 LV.3

from itertools import product
import heapq


def solution(k, n, reqs):
    answer = float('inf')
    
    for prod in product(range(1, n + 1), repeat=k):
        if sum(prod) == n:
            mentors = [[0 for _ in range(a)] for a in prod]
            waiting = 0
            
            for start, progress, category in reqs:
                now = heapq.heappop(mentors[category - 1])
                heapq.heappush(mentors[category - 1], max(now, start) + progress)
                waiting += max(0, now - start)
                
            answer = min(answer, waiting)
    
    return answer