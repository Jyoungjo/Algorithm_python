# 백준 9017 크로스 컨트리 -


import sys
from collections import defaultdict

input = sys.stdin.readline
N = int(input())


def solution():
    for _ in range(N):
        T = int(input())
        scores, team_cnt = defaultdict(list), defaultdict(int)
        teams = list(map(int, input().split()))
        for i in range(T):
            team_cnt[teams[i]] += 1

        a = []
        for k, v in team_cnt.items():
            if v >= 6:
                a.append(k)

        score = 1
        for i in range(T):
            if teams[i] in a:
                if not scores[teams[i]]:
                    scores[teams[i]] = [score]
                else:
                    scores[teams[i]].append(score)
                score += 1

        result = defaultdict(tuple)
        for k, v in scores.items():
            result[k] = (sum(v[:4]), v[4])

        answer, s, five = 0, float('inf'), 0
        for k, v in result.items():
            if s > v[0]:
                answer = k
                s = v[0]
                five = v[1]
            elif s == v[0]:
                if five > v[1]:
                    five = v[1]
                    answer = k

        print(answer)


solution()