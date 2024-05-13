def solution(num):
    t = num
    cnt = 0
    while True:
        if t == 1:
            return cnt
        elif cnt == 500:
            return -1
        if t % 2 == 0:
            t /= 2
        else:
            t = t * 3 + 1
        cnt += 1