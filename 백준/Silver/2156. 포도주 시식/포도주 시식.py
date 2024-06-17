# 백준 2156 포도주 시식 - 실버 1

# 전에 했던 행동이 다음 행동에 영향을 미친다. -> 계단 오르기?
# 규칙 찾아보기
# 1. 마지막 포도주 안 마심
# 2. 마지막 포도주 먹고 직전 포도주 마심 -> 이러면 직전의 전 포도주는 마시면 안된다.
# 3. 마지막 포도주 먹고 직전 포도주 안 마심
import sys
input = sys.stdin.readline


def solution(n, wines):
    if n == 1:
        return wines[1]
    
    dp = [0] * 10001
    dp[1] = wines[1]
    dp[2] = wines[1] + wines[2]
    for i in range(3, n + 1):
        dp[i] = max(dp[i - 1], wines[i] + wines[i - 1] + dp[i - 3], wines[i] + dp[i - 2])
    return max(dp)


def main():
    n = int(input())
    wines = [0] + list(int(input()) for _ in range(n)) + [0]
    return print(solution(n, wines))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()