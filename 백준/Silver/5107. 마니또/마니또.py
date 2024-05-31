# 백준 5107 마니또 - 실버 1

# 연결고리가 생기는지 확인
# dictionary 사용해서 start -> end 형식으로 진행
import sys
from collections import defaultdict, deque


def bfs(begin):
    q = deque()
    q.append(begin)
    while q:
        target = q.popleft()
        if not target:
            return 0

        visited[target] = True
        if visited[linked[target]]:
            return 1
        else:
            q.append(linked[target])


case_num = 1

while True:
    linked = defaultdict(str)
    visited = defaultdict(bool)
    member = list()
    count = 0
    N = int(sys.stdin.readline())
    if N == 0:
        break

    for i in range(N):
        start, end = sys.stdin.readline().strip().split()
        linked[start] = end
        visited[start] = False
        member.append(start)

    for m in member:
        if not visited[m]:
            count += bfs(m)

    print(case_num, count)
    case_num += 1