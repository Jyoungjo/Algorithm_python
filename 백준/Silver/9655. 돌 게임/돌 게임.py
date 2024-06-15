# 백준 9655 돌 게임 - 실버 5

# 이거 그냥 홀수면 SK, 짝수면 CY 인데..
# dp는 턴 수 저장하면 될듯
import sys
input = sys.stdin.readline


def find_winner(N):
    dp = [0] * 1001
    dp[1] = 1
    dp[2] = 2
    dp[3] = 1
    for i in range(4, N+1):
        dp[i] = dp[i-1] + 1
    return dp[N]


def main():
    N = int(input())
    return print('SK' if find_winner(N) % 2 == 1 else 'CY')


main()