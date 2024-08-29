# 백준 1205 등수 구하기 -


import sys
input = sys.stdin.readline
N, new_score, P = map(int, input().split())


def solution():
    if N == 0:
        return print(1)

    ranking = list(map(int, input().split()))

    if N == P and ranking[-1] >= new_score:
        return print(-1)
    else:
        answer = N + 1
        for i in range(N):
            if ranking[i] <= new_score:
                answer = i + 1
                break
        return print(answer)


solution()