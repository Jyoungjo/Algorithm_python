# 백준 5525 IOIOI - 실버 1

# Pn = N+1개의 I와 N개의 O로 이루어진 문자열
# Pn 만드는 과정에서 메모리 초과 -> 문자열 더하기 연산을 append로 해결
# 이후 시간 초과 -> 슬라이싱으로 비교했는데 여기서 문제?
import sys


N = int(sys.stdin.readline().strip())
M = int(sys.stdin.readline().strip())
S = sys.stdin.readline().strip()

cnt, idx, pattern = 0, 1, 0
while idx < M - 1:
    if S[idx - 1] == 'I' and S[idx] == 'O' and S[idx + 1] == 'I':
        pattern += 1
        if pattern == N:
            pattern -= 1
            cnt += 1
        idx += 1
    else:
        pattern = 0
    idx += 1

print(cnt)