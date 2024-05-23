def solution(s):
    answer = [0, 0]
    cnt = 0
    while True:
        zero = 0
        if s == '1':
            answer[0] = cnt
            break
        for c in s:
            if c == '0':
                zero += 1
        s = bin(len(s.replace('0', '')))[2:]
        answer[1] += zero
        cnt += 1
    return answer