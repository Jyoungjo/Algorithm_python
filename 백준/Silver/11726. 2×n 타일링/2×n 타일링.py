# 백준 11726 2*n 타일링 - 실버 3

# n = 1 -> 1
# n = 2 -> 2
# n = 3 -> 3
# n = 4 -> 5
# n = 5 -> 8
# 피보나치 수열 구하기랑 같음
import sys
input = sys.stdin.readline


def solution(n):
    MOD = 10007
    dp = [0] * 1001
    dp[1] = 1
    dp[2] = 2
    for i in range(3, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n] % MOD


def main():
    n = int(input())
    return print(solution(n))


main()