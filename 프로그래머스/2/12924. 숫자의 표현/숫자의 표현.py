def solution(n):
    answer = 0
    l, r, p_sum = 0, 0, 0
    while l < n:
        if p_sum < n:
            r += 1
            p_sum += r
        else:
            if p_sum == n:
                answer += 1
            l += 1
            p_sum -= l
    return answer