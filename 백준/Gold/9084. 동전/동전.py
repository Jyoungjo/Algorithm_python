# 백준 9084 동전 - 골드 5

# 동전이 오름차순 별로 정렬되어있고 제일 작은 단위부터 가짓수 세기
# 현재 구하려는 숫자에서 동전 값을 뺐을 때, 결과값이 있다면 동전으로 표현이 가능하므로 가짓수 + 1
import sys
input = sys.stdin.readline


def count_all_nums(M, coins):
    dp = [0] * (M+1)
    dp[0] = 1
    for coin in coins:
        for i in range(M + 1):
            if i >= coin:
                dp[i] += dp[i - coin]
    return dp[M]


def solution(T):
    result = []
    for _ in range(T):
        N = int(input())
        coins = list(map(int, input().split()))
        M = int(input())
        result.append(count_all_nums(M, coins))
    return result


def main():
    T = int(input())
    return print('\n'.join(map(str, solution(T))))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()