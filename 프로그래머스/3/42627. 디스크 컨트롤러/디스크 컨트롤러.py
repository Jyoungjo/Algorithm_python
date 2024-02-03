# 프로그래머스 42627 디스크 컨트롤러 LV.3
import heapq


def solution(jobs):
    answer, now, i = 0, 0, 0
    start = -1
    wait_list = []

    while i < len(jobs):
        for j in jobs:
            if start < j[0] <= now:
                heapq.heappush(wait_list, (j[1], j[0]))

        if wait_list:
            cur = heapq.heappop(wait_list)
            start = now
            now += cur[0]
            answer += now - cur[1]
            i += 1
        else:
            now += 1

    return answer // i