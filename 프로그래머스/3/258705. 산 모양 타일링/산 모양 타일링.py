# 프로그래머스 258705 산 모양 타일링 LV.3
def solution(n, tops):
    divider = 10007
    dp1 = [0] * n
    dp2 = [0] * n
    dp1[0] = 1
    dp2[0] = 2 + tops[0]

    for i in range(1, n):
        dp1[i] = (dp1[i - 1] + dp2[i - 1]) % divider
        dp2[i] = (dp1[i - 1] * (1 + tops[i]) + dp2[i - 1] * (2 + tops[i])) % divider

    return (dp1[-1] + dp2[-1]) % divider