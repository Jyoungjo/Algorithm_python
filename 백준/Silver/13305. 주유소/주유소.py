# 백준 13305 주유소 - 실버 3

# 주유소에서 기름을 최소로 넣는 문제
# 현재 기름 가격 > 다음 기름 가격 -> 지금 주유소에서는 다음 주유소까지 가는 도로를 건널수 있을만큼만 넣고 다음 주유소 이동
# 아니라면 거리를 저장해놓고 다음 주유소와 비교
import sys
input = sys.stdin.readline


def solution(N, roads, oils):
    res, idx = 0, 0
    stack = []
    for i in range(N):
        stack.append(roads[i])
        if oils[idx] > oils[i]:
            while stack:
                res += oils[idx] * stack.pop()
            idx = i
    if stack:
        while stack:
            res += oils[idx] * stack.pop()
    return res


def main():
    N = int(input())
    roads = [0] + list(map(int, input().split()))
    oils = list(map(int, input().split()))
    return print(solution(N, roads, oils))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()