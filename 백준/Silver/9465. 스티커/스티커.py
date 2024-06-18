# 백준 9465 스티커 - 실버 1

# 스티커의 상황: 마지막 스티커 떼고 + 이전 스티커를 뗐냐 안 뗐냐로 갈림
# 이전 스티커를 뗀 경우에는 자신 대각방향을 떼야하고
# 안 뗀 경우에는 마지막 스티커 전전 상황의 가장 큰 값에 더해주면 된다.
import sys
input = sys.stdin.readline


def find_max_sum(n, stickers):
    if n == 1:
        return max(stickers[0][1], stickers[1][1])
    
    dp = [[0] * 100001 for _ in range(2)]
    dp[0][1] = stickers[0][1]
    dp[1][1] = stickers[1][1]
    dp[0][2] = stickers[1][1] + stickers[0][2]
    dp[1][2] = stickers[0][1] + stickers[1][2]
    for j in range(3, n + 1):
        for i in range(2):
            dp[i][j] = stickers[i][j] + max(dp[1-i][j-1], max(dp[i][j-2], dp[1-i][j-2]))
    return max(dp[0][n], dp[1][n])


def solution(T):
    result = []
    for _ in range(T):
        n = int(input())
        stickers = [[0] + list(map(int, input().split())) for _ in range(2)]
        result.append(find_max_sum(n, stickers))
    return result


def main():
    T = int(input())
    return print(*solution(T))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()