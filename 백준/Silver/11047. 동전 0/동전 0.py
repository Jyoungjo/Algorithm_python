# 백준 11047 동전 0 - 실버 4

# 동전의 개수가 최솟값이 되어야 함 + 동전의 가치가 배수관계 -> 그리디
# 가치가 큰 동전부터 순회하면서 K보다 작거나 같을 때 동전 개수 체크
import sys
input = sys.stdin.readline


def solution(K, coins):
    cnt = 0
    for coin in coins:
        if K == 0:
            break
        if coin <= K:
            cnt += K // coin
            K %= coin
    return cnt


def main():
    N, K = map(int, input().split())
    coins = sorted(list(int(input()) for _ in range(N)), reverse=True)
    return print(solution(K, coins))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()