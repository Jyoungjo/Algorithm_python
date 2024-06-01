# 백준 5525 IOIOI - 실버 1

# Pn = N+1개의 I와 N개의 O로 이루어진 문자열
import sys


N = int(sys.stdin.readline().strip())
M = int(sys.stdin.readline().strip())
S = sys.stdin.readline().strip()

# Pn 만들기
arr = ['I']
for i in range(N):
    arr.append('OI')
Pn = ''.join(arr)

cnt, idx = 0, 0
# 문자열 S에서 I로 시작하는 부분부터 슬라이싱해서 일치여부 확인
while idx < M:
    if S[idx] == 'I' and S[idx:idx+len(Pn)] == Pn:
        cnt += 1
        idx += 2
    else:
        idx += 1
print(cnt)