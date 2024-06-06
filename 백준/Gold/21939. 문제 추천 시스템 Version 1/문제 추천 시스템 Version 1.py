# 백준 21939 문제 추천 시스템 Version 1 - 골드 4

# heap 사용해서 난이도 -> 번호 순으로 pop (쉬운 문제 = 난이도 -> 번호 작은 순 / 어려운 문제 = 난이도 -> 번호 큰 순)
# solved 하게 되면 heap에서 인덱스로 찾을 수 없을 것 -> dict 만들어서 heap에서 제거했다는 여부 판별
import sys
import heapq
from collections import defaultdict

input = sys.stdin.readline


def update_max_val(check, max_heap):
    while not check[-max_heap[0][1]]:
        heapq.heappop(max_heap)


def update_min_val(check, min_heap):
    while not check[min_heap[0][1]]:
        heapq.heappop(min_heap)


def run_recommend_system(command, min_heap, max_heap, check):
    if 'add' in command:
        update_max_val(check, max_heap)
        update_min_val(check, min_heap)
        heapq.heappush(min_heap, (int(command[2]), int(command[1])))
        heapq.heappush(max_heap, (-int(command[2]), -int(command[1])))
        check[int(command[1])] = True
    elif 'recommend' in command:
        if command[1] == '1':
            # 제일 어려운 문제가 이미 풀렸는데 heap에 남아있을 수 있다.
            update_max_val(check, max_heap)
            return -max_heap[0][1]
        else:
            # 제일 쉬운 문제가 이미 풀렸는데 heap에 남아있을 수 있다.
            update_min_val(check, min_heap)
            return min_heap[0][1]
    else:
        check[int(command[1])] = False

    return None


def main():
    N = int(input())
    min_heap, max_heap = [], []
    check = defaultdict(bool)
    for _ in range(N):
        num, level = map(int, input().strip().split())
        heapq.heappush(min_heap, (level, num))
        heapq.heappush(max_heap, (-level, -num))
        check[num] = True
    M = int(input())
    for _ in range(M):
        command = input().strip().split()
        res = run_recommend_system(command, min_heap, max_heap, check)
        print(res) if res else 0


main()