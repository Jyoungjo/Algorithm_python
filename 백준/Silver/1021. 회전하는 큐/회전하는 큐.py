# 백준 1021 회전하는 큐 - 실버 3

# deque의 rotate 이용해서 해결한다.
# 내가 찾는 숫자를 기준으로 양 옆에 몇 개가 있는지 보고 개수 더 적은 방향으로 회전
import sys
from collections import deque


input = sys.stdin.readline
N, M = map(int, input().strip().split())
arr = [x for x in map(int, input().strip().split())]
q = deque([x for x in range(1, N + 1)])
cnt = 0
for n in arr:
    idx = q.index(n)
    if idx <= len(q) - idx:
        q.rotate(-idx)
        cnt += idx
    else:
        q.rotate(len(q) - idx)
        cnt += (len(q) - idx)
    q.popleft()
print(cnt)