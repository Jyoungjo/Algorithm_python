# 프로그래머스 12914 멀리 뛰기 LV.2
def solution(n):
    dp = [0] * (n+1)
    dp[0], dp[1] = 1, 1
    for i in range(2, n+1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[-1] % 1234567