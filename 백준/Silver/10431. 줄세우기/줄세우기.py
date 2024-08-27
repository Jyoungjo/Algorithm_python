# 백준 10431 줄세우기 -


import sys

input = sys.stdin.readline
T = int(input())


def solution():
    for _ in range(T):
        a = list(map(int, input().split()))
        num, people = a[0], a[1:]
        cnt = 0
        for i in range(len(people) - 1):
            for j in range(i + 1, len(people)):
                if people[i] > people[j]:
                    people[i], people[j] = people[j], people[i]
                    cnt += 1
        print(num, cnt)


solution()
