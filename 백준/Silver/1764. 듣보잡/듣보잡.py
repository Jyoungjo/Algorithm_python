# 백준 1764 듣보잡 - 실버 4

# 각각의 집합의 교집합의 개수와 사전순으로 정렬된 원소 출력
import sys


N, M = map(int, sys.stdin.readline().split())
A = set(sys.stdin.readline().strip() for _ in range(N))
B = set(sys.stdin.readline().strip() for _ in range(M))
print(len(A & B))
print('\n'.join(sorted(A & B)))