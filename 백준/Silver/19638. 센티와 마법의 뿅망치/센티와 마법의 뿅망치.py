# 백준 19638 센티와 마법의 뿅망치 - 실버 1

# 우선순위 큐 사용해서 제일 키가 큰 거인의 키를 반으로 줄여나간다.
# T만큼 진행하고 종료 -> T가 엄청 큰데 target = 1 이라면 바로 종료
import sys
from heapq import heapify, heapreplace
input = sys.stdin.readline


def use_the_magic(centi_H, T, giants):
    cnt = 0
    for i in range(T):
        if -giants[0] == 1 or -giants[0] < centi_H:
            break
        else:
            heapreplace(giants, -(-giants[0] // 2))
            cnt += 1

    return check_max_val(cnt, centi_H, giants)


def check_max_val(cnt, centi_H, giants):
    if -giants[0] >= centi_H:
        return 'NO', -giants[0]
    return 'YES', cnt


def main():
    N, centi_H, T = map(int, input().split())
    giants = [-int(input()) for _ in range(N)]
    heapify(giants)
    return use_the_magic(centi_H, T, giants)


print('\n'.join(map(str, main())))