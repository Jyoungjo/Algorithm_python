# 백준 1461 도서관 - 골드 4

# 책을 옮길 수 있는 최소 걸음 수
# 절댓값이 제일 큰 수는 마지막에 놓고 끝 / 그게 아니라면 왕복으로 진행
# 양수 방향으로 가는 경우와 음수 방향으로 가는 경우 두 개로 분리
# 각 영역별로 제일 큰 수 부터 M만큼 책 놓고 온 다음에 거기서 절댓값 가장 큰 애 빼주자
import sys
from heapq import heappush, heappop
input = sys.stdin.readline


def cal_total_steps(p, n):
    return p[0] + n[0] - max(p[1], n[1])


def cal_each_steps(M, h):
    total_cnt, tmp = 0, 0
    while h:
        dist = heappop(h)
        if not tmp:
            tmp = dist

        for i in range(M-1):
            if not h:
                break
            heappop(h)

        total_cnt += dist * 2
    return -total_cnt, -tmp


def divide_arr(books):
    p, n = [], []
    for book in books:
        if book > 0:
            heappush(p, -book)
        else:
            heappush(n, book)
    return p, n


def solution(M, books):
    positive, negative = divide_arr(books)
    p, n = cal_each_steps(M, positive), cal_each_steps(M, negative)
    return cal_total_steps(p, n)


def main():
    N, M = map(int, input().split())
    books = list(map(int, input().split()))
    return print(solution(M, books))


main()