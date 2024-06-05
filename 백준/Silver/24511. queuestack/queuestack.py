# 백준 24511 queuestack - 실버 3

# 각각의 자료구조를 거쳐가면서 최종 나오는 x_n을 리턴하는 queuestack 만들기
# 0이면 Queue 1이면 Stack
# 시간초과 -> O(M*N) -> Stack은 들어왔다가 다시 나가니까 고려하지 말자 -> O(M)

import sys
from collections import deque
input = sys.stdin.readline


def operate_queuestack(arr_by_type, element):
    arr_by_type.appendleft(element)
    return arr_by_type.pop()


def main():
    N = int(input())
    types = [x for x in map(int, input().split())]
    arr = [y for y in map(int, input().split())]
    arr_by_type = deque([x for t, x in zip(types, arr) if t == 0])
    M = int(input())
    q_s_arr = [x for x in map(int, input().split())]
    ans = []
    for i in range(M):
        ans.append(operate_queuestack(arr_by_type, q_s_arr[i]))
    return ans


print(*main())