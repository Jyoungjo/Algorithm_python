# 백준 11399 ATM - 실버 4


import sys
input = sys.stdin.readline


def cal_accumulated_sum(times):
    total_time, current_time = 0, 0
    for time in times:
        current_time += time
        total_time += current_time
    return total_time


def main():
    N = int(input())
    times = sorted([val for val in map(int, input().strip().split())])
    return cal_accumulated_sum(times)


print(main())