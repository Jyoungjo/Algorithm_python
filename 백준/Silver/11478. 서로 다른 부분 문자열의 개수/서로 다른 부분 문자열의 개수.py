# 백준 11478 서로 다른 부분 문자열의 개수 - 실버 3

# 문자열 S의 서로 다른 부분 문자열의 개수 구하기
# 부분 문자열 = 연속된 부분
# 슬라이싱으로 처리하기
import sys


S = sys.stdin.readline().strip()
ans = set()
for i in range(len(S)):
    for j in range(i, len(S)):
        ans.add(S[i:j+1])
print(len(ans))