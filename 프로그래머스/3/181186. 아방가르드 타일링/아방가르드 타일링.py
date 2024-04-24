# 프로그래머스 181186 아방가르드 타일링 LV.3

def solution(n):
    divider = 1000000007
    dp = [0, 1, 3, 10, 23, 62, 170]

    if n < 7:
        return dp[n]

    for i in range(7, n + 1):
        x = (dp[-1] + 2 * dp[-2] + 6 * dp[-3] + dp[-4] - dp[-6]) % divider
        dp = dp[1:] + [x]

    return dp[-1]