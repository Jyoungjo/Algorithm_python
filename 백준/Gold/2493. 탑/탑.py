# 백준 2493 탑 - 골드 5

# O(N^2) = 2500억
# 최소 O(NlogN)
# 탑들을 뒤에서 부터 순회하면서 자신보다 큰 타워가 있다면 스택이 비워질 때까지 해당 타워의 인덱스 + 1 기록
# 없다면 스택에 저장하고 다음 타워 순회
import sys
input = sys.stdin.readline


N = int(input())
towers = [(i, x) for i, x in enumerate(map(int, input().split()))]
stack = []
answer = [0] * N
for i in range(N - 1, -1, -1):
    idx, target = towers[i]
    if not stack:
        stack.append((idx, target))
        continue

    while stack and stack[-1][1] < target:
        answer[stack[-1][0]] = idx + 1
        stack.pop()
    stack.append((idx, target))

print(0) if all(x == 0 for x in answer) else print(*answer)