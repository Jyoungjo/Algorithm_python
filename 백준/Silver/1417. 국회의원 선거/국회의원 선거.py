# 백준 1417 국회의원 선거 - 실버 5

# 다솜이보다 크거나 같은 수가 존재하면 해당 득표 수를 가져온다.
# 후보자가 없으면 0 리턴
# 다른 후보의 득표수를 최대 힙으로 만들어서 맨 앞자리랑 다솜이의 득표수를 비교하고 조건에 맞으면 다솜이 득표수를 늘려준다.

import sys
from heapq import heapify, heappop, heappush
input = sys.stdin.readline


def rig_election(dasom, people):
    cnt = 0
    if not people:
        return cnt

    while -people[0] >= dasom:
        max_val = -heappop(people)
        if max_val >= dasom:
            dasom += 1
            cnt += 1
            heappush(people, -max_val + 1)
    return cnt


def main():
    N = int(input())
    dasom = int(input())
    people = [-int(input()) for _ in range(N-1)]
    heapify(people)
    return rig_election(dasom, people)


print(main())