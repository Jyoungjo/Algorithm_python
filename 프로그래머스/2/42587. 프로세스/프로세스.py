# 프로그래머스 42587 프로세스 LV.2
def solution(priorities, location):
    answer = 0
    q = [(i, p) for i, p in enumerate(priorities)]
    while q:
        i, priority = q.pop(0)
        if any(priority < p[1] for p in q):
            q.append((i, priority))
        else:
            answer += 1
            if i == location:
                break
    return answer