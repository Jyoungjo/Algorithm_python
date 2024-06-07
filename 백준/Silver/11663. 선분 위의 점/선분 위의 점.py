# 백준 11663 선분 위의 점 - 실버 3


# 주어진 범위 내에 숫자가 몇 개인지 확인
# bisect 라이브러리 사용 -> 주어진 범위에서 왼쪽과 오른쪽 끝 값이 배열의 어느 부분에 들어가야 되는지 확인
import sys
from bisect import bisect_left, bisect_right
input = sys.stdin.readline


def find_point(arr, M):
    res = []
    for _ in range(M):
        left, right = map(int, input().split())
        res.append(bisect_right(arr, right) - bisect_left(arr, left))
    return res


def main():
    N, M = map(int, input().split())
    arr = sorted(list(map(int, input().split())))
    return '\n'.join(map(str, find_point(arr, M)))


print(main())