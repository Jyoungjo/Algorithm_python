# 백준 2839 설탕 배달 - 실버 4

# N = (N-3) + 3, (N-5) + 5 와 같다. -> 이전 결과값에 3킬로나 5킬로 한 봉지 추가하는 형태
import sys
input = sys.stdin.readline


def solution(N):
    INF = float('inf')
    dp = [INF] * 5001
    dp[3] = 1
    dp[5] = 1
    for i in range(6, N+1):
        dp[i] = min(dp[i - 3], dp[i - 5]) + 1
    return dp[N] if dp[N] != INF else -1


def main():
    N = int(input())
    return print(solution(N))


main()