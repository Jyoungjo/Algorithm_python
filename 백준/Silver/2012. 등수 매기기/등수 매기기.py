# 백준 2012 등수 매기기 - 실버 3

# 예상 등수 A, 실제 등수 B 라면 불만도 = |A - B|
# 불만도의 총 합을 최소로 하면서 등수 매기기 -> 차이가 없어야 한다
# 오름차순 정렬한 다음 인덱스+1 한 값과 예상 순위의 차를 누적한다.
import sys
input = sys.stdin.readline


def cal_dissatisfaction(ranks):
    res = 0
    for i, r in enumerate(ranks):
        res += abs((i + 1) - r)
    return res


def main():
    N = int(input())
    ranks = sorted([int(input()) for _ in range(N)])
    return print(cal_dissatisfaction(ranks))


main()