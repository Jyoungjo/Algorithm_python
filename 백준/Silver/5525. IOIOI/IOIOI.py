# 백준 5525 IOIOI - 실버 1

# Pn = N+1개의 I와 N개의 O로 이루어진 문자열
import sys


N = int(sys.stdin.readline().strip())
M = int(sys.stdin.readline().strip())
S = sys.stdin.readline().strip()

# Pn 만들기
Pn = [''] * (N+1)
Pn[0] = 'I'
for i in range(1, N+1):
    Pn[i] = Pn[i-1] + 'OI'

cnt = 0
# 문자열 S에서 I로 시작하는 부분부터 슬라이싱해서 일치여부 확인
for i in range(M):
    if S[i] == 'I':
        if S[i:i+len(Pn[-1])] == Pn[-1]:
            cnt += 1
print(cnt)