# 백준 15829 Hashing - 브론즈 2

import sys
input = sys.stdin.readline


L = int(input())
S = str(input().strip())
r = 31
M = 1234567891
ans = 0
for i in range(len(S)):
    ans += (ord(S[i]) - ord('a') + 1) * r ** i
print(ans % M)