# 백준 2346 풍선 터뜨리기 - 실버 3

# 첫 번째 풍선부터 터뜨리고 나온 숫자대로 로테이션해서 계속 터뜨리면 된다.
import sys
from collections import deque


input = sys.stdin.readline
N = int(input())
q = deque([(x, i) for i, x in enumerate(map(int, input().split()))])
ans = []
for _ in range(N):
    mv_cnt, idx = q.popleft()
    ans.append(idx + 1)
    mv_cnt -= 1 if mv_cnt > 0 else 0
    q.rotate(-mv_cnt)
print(*ans)