# 백준 1931 회의실 배정 - 실버 1

# 끝나는 시간 기준 오름차순 정렬 후 회의 개수 카운트
import sys
input = sys.stdin.readline


def count_meetings(N, meetings):
    cnt, time = 0, 0
    for i in range(N):
        start, end = meetings[i]
        if start >= time:
            time = end
            cnt += 1
    return cnt


def main():
    N = int(input())
    meetings = sorted([list(map(int, input().split())) for _ in range(N)], key=lambda x: (x[1], x[0]))
    return print(count_meetings(N, meetings))


main()