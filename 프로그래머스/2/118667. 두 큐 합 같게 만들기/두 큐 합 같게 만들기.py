# 프로그래머스 118667 두 큐 합 같게 만들기


from collections import deque


def solution(queue1, queue2):
    answer = 0
    queue1, queue2 = deque(queue1), deque(queue2)
    target = (sum(queue1) + sum(queue2)) // 2
    limit = (len(queue1) + len(queue2)) * 2
    if max(queue1) > target or max(queue2) > target or (sum(queue1) + sum(queue2)) % 2 != 0:
        return -1

    a = sum(queue1)
    while True:
        if answer > limit:
            return -1
        
        if a < target:
            t = queue2.popleft()
            queue1.append(t)
            a += t
        elif a > target:
            t = queue1.popleft()
            queue2.append(t)
            a -= t
        else:
            return answer
        answer += 1