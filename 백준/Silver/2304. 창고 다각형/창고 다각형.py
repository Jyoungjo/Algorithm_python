# 백준 2304 창고 다각형 - 실버 2

# 주어진 기둥의 l 값에 따라 오름차순 정리
# 최고 높이를 가진 기둥 양 옆으로 넓이 구해준다.
import sys
input = sys.stdin.readline


def make_polygon(materials):
    idx, area = 0, 0
    # 최고 기둥 높이 + 인덱스 찾기
    for i in range(len(materials)):
        if materials[i][1] > area:
            area = materials[i][1]
            idx = i

    # 최고 기둥 기준 왼쪽
    max_h = materials[0][1]
    for i in range(idx):
        if max_h < materials[i+1][1]:
            area += max_h * (materials[i+1][0] - materials[i][0])
            max_h = materials[i+1][1]
        else:
            area += max_h * (materials[i + 1][0] - materials[i][0])

    # 최고 기둥 기준 오른쪽
    max_h = materials[-1][1]
    for i in range(len(materials) - 1, idx, -1):
        if max_h < materials[i - 1][1]:
            area += max_h * (materials[i][0] - materials[i-1][0])
            max_h = materials[i - 1][1]
        else:
            area += max_h * (materials[i][0] - materials[i - 1][0])
    return area


def main():
    N = int(input())
    pillars = sorted([tuple(map(int, input().split())) for _ in range(N)])
    return make_polygon(pillars)


print(main())