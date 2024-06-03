# 백준 10868 최솟값 - 골드 1

# N개의 원소 중에서 a에서 b까지 범위의 수 중 최솟값을 찾는 문제
# N개의 원소를 주어진 범위로 슬라이싱해서 heap에 넣고 pop 해보자
# -> 시간 초과 -> 세그먼트 트리?
import sys


# 세그먼트 트리 생성
def init(start, end, index):
    # 해당 구간이 리프노드라면
    if start == end:
        tree[index] = nums[start]
        return tree[index]

    mid = (start + end) // 2
    tree[index] = min(init(start, mid, index * 2), init(mid + 1, end, index * 2 + 1))
    return tree[index]


def find_min_val(start, end, index, left, right):
    # 범위를 완전히 벗어난 경우(범위가 오른쪽으로 넘어갔거나 왼쪽으로 넘어간 경우)
    if left > end or right < start:
        return float('inf')

    # 범위 안에 있는 경우(left, right 사이에 start, end가 있는 경우)
    if left <= start and end <= right:
        return tree[index]

    # 그렇지 않은 경우 -> 구간을 좁힌다.
    mid = (start + end) // 2
    return min(find_min_val(start, mid, index * 2, left, right), find_min_val(mid + 1, end, index * 2 + 1, left, right))


N, M = map(int, sys.stdin.readline().strip().split())
nums = [0] + [int(sys.stdin.readline().strip()) for _ in range(N)]
tree = [0] * (N * 4)
init(1, N, 1)
for i in range(M):
    a, b = map(int, sys.stdin.readline().strip().split())
    print(find_min_val(1, N, 1, a, b))
