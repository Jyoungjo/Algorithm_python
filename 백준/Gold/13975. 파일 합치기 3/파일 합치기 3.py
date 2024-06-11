# 백준 13975 파일 합치기 3 - 골드 4

# 제일 작은 파일 두 개 꺼내서 합치고 그 비용을 누적한다. -> heapq 사용 
import sys
from heapq import heapify, heappush, heappop
input = sys.stdin.readline


def calculate_the_cost_of_pages(pages):
    heapify(pages)
    ans = 0
    while len(pages) > 1:
        cost = heappop(pages) + heappop(pages)
        ans += cost
        heappush(pages, cost)
    return ans


def main():
    T = int(input())
    answer = []
    for _ in range(T):
        N = int(input())
        pages = list(map(int, input().split()))
        answer.append(calculate_the_cost_of_pages(pages))
    return print('\n'.join(map(str, answer)))


main()