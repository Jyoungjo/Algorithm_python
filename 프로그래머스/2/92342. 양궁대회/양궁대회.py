from itertools import product


def solution(n, info):
    info = info[::-1]
    answer = [-1]
    max_v = 0
    for win_lose in product((True, False), repeat=11):
        t = 0
        used_arrow = sum(info[i] + 1 for i in range(11) if win_lose[i])
        if used_arrow <= n:
            apeach = sum(i for i in range(11) if not win_lose[i] and info[i])
            ryan = sum(i for i in range(11) if win_lose[i])
            d = ryan - apeach
            if d > max_v:
                max_v = d
                answer = [info[i] + 1 if win_lose[i] else 0 for i in range(11)]
                answer[0] += n - used_arrow
    answer = answer[::-1]
    return answer