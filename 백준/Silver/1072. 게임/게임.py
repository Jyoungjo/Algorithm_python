# 백준 1072 게임 - 실버 3

# 게임 횟수를 늘려가면서 승률의 변화를 찾는 문제
# 게임 횟수 = 10억 -> 이분 탐색
# 게임 횟수에 대해 이분 탐색 진행
# 내 이전 승률과 mid 만큼 게임을 한 뒤의 승률을 비교해서 다르다면 최소의 값을 찾기 위해 right = mid - 1
# 동일하다면 판수의 변화를 줘야하므로 left = mid + 1
# 승률 계산할 때, 연산 순서 고려해서 100 곱하고 진행 -> 나눗셈 연산에서 소수점 오차가 생길 수 있다.

import sys
input = sys.stdin.readline


def play_the_game(X, Y):
    res, left, right = 0, 1, X
    z = (Y * 100) // X
    if z >= 99:
        return -1
    while left <= right:
        mid = (left + right) // 2
        new_z = (100 * (Y + mid)) // (X + mid)
        if new_z > z:
            right = mid - 1
            res = mid
        else:
            left = mid + 1
    return res


def main():
    X, Y = map(int, input().split())
    return play_the_game(X, Y)


print(main())