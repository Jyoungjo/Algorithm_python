# 백준 1546 평균 - 브론즈 1

# 자기 점수 최대값 M
# 모든 점수 = 해당 점수/M*100 수정
# 평균 구하기
import sys


total = 0
N = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))
M = max(arr)
for x in arr:
    total += x / M * 100
print(total / N)