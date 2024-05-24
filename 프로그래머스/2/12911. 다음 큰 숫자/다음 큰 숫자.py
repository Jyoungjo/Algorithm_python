def solution(n):
    nxt = n + 1
    while True:
        if len(bin(n)[2:].replace('0', '')) == len(bin(nxt)[2:].replace('0', '')):
            return nxt
        else:
            nxt += 1