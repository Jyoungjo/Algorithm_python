# 백준 20291 파일 정리 - 실버 3

# 파일의 확장자 별 정리 -> 몇 개 있는지
# 확장자 사전 순 정렬
import sys
from collections import defaultdict


N = int(sys.stdin.readline())
extensions = defaultdict(int)
for x in range(N):
    n, e = sys.stdin.readline().split('.')
    extensions[e[:-1]] += 1

for k, v in sorted(extensions.items()):
    print(k, v)