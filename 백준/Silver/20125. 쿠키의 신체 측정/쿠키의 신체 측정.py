# 백준 20125 쿠키의 신체 측정 -


import sys
input = sys.stdin.readline
N = int(input())
cookie = list(input().strip() for _ in range(N))


def solution():
    heart = ()
    body = [0, 0, 0, 0, 0]
    f = True
    # 심장 찾기
    for i in range(N):
        for j in range(N):
            if cookie[i][j] == '*':
                heart = (i + 2, j + 1)
                f = False
                break
        if not f:
            break

    for i in range(N):
        for j in range(N):
            if cookie[i][j] == '*':
                # 머리라면 패스
                if i == heart[0] - 2 and j == heart[1] - 1:
                    continue

                # 팔 찾기
                if i == heart[0] - 1:
                    if j < heart[1] - 1:
                        body[0] += 1
                    elif j > heart[1] - 1:
                        body[1] += 1
                # 허리 찾기
                elif j == heart[1] - 1:
                    body[2] += 1
                # 다리 찾기
                else:
                    if j < heart[1] - 1:
                        body[3] += 1
                    elif j > heart[1] - 1:
                        body[4] += 1
                        
    print(' '.join(map(str, heart)))
    print(' '.join(map(str, body)))


solution()