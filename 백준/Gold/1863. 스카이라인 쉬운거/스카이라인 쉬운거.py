# 백준 1863 스카이라인 쉬운거 - 1863

# 지금 건물보다 고도가 낮은 건물을 만나면 해당 건물과 다른 건물이므로 카운트 증가
# 마지막 건물 처리하기 위해 0 추가
import sys
input = sys.stdin.readline


def count_buildings(coord):
    cnt, stack = 0, [0]
    for h in coord:
        height = h
        while stack[-1] > h:
            if stack[-1] != height:
                cnt += 1
                height = stack[-1]
            stack.pop()
        stack.append(h)
    return cnt


def main():
    n = int(input())
    coord = [list(map(int, input().split()))[1] for _ in range(n)]
    coord.append(0)
    return print(count_buildings(coord))


main()