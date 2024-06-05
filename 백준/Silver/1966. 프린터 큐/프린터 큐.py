# 백준 1966 프린터 큐 - 실버 3

# queue에서 중요도 확인 -> 자신보다 숫자 큰게 하나라도 있는지 -> any 사용
# 있으면 맨 뒤로 없으면 인쇄 -> 근데 내가 찾던 인덱스의 숫자다? 바로 print
# 그럼 중요도에 순서를 지정해줘야함. -> enumerate
import sys
from collections import deque
input = sys.stdin.readline


T = int(input())
for _ in range(T):
    N, idx = map(int, input().strip().split())
    q = deque()
    for i, x in enumerate(map(int, input().strip().split())):
        q.append((x, i))
    cnt = 1
    while q:
        num, target = q.popleft()
        if any(num < x[0] for x in q):
            q.append((num, target))
        else:
            if idx == target:
                print(cnt)
                break
            else:
                cnt += 1