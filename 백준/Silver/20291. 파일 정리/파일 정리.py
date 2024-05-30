# 백준 20291 파일 정리 - 실버 3

# 파일의 확장자 별 정리 -> 몇 개 있는지
# 확장자 사전 순 정렬
import sys
from collections import Counter

N = int(sys.stdin.readline())
extensions = [sys.stdin.readline().strip().split('.')[1] for _ in range(N)]
print("\n".join(f"{k} {v}" for k, v in sorted(Counter(extensions).items())))