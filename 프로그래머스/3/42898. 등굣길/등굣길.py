# 프로그래머스 42898 등굣길 LV.3
def solution(m, n, puddles):
    answer = 0
    dp = [[1] * m for _ in range(n)]
    denominator = 1000000007
    
    for y, x in puddles:
        if y == 1:
            for i in range(x - 1, n):
                dp[i][y - 1] = 0
        if x == 1:
            for i in range(y - 1, m):
                dp[x - 1][i] = 0
        dp[x - 1][y - 1] = 0
    
    for x in range(n):
        for y in range(m):
            if 0 <= x - 1 <= n and 0 <= y - 1 <= m and dp[x][y] != 0:
                dp[x][y] = dp[x - 1][y] + dp[x][y - 1]
                
    return dp[-1][-1] % denominator