# 백준 16926 배열 돌리기 1 - 골드 5

# 배열 회전하기
# 배열을 순회하면서 각 원소를 실제 회전 방향으로 이동시킨 인덱스에다가 값 업데이트 하기
# 했는데 시간초과 -> 그렇게 크다고 생각 안했는데..
# 그럼 depth에 따라 idx로 구분해서 저장해놓고 회전한 위치 따로 구해서 값 업데이트 하는 방식으로 진행 
import sys


# 외각 배열 depth 순으로 분류 (상 -> 우 -> 하 -> 좌 순으로 진행)
def classify_array_by_depth():
    depth = 0
    nums = [[] for _ in range(depth_count)]
    while depth < depth_count:
        # 상
        for c in range(depth, M - depth):
            nums[depth].append(arr[depth][c])
        # 우
        for r in range(depth + 1, N - depth):
            nums[depth].append(arr[r][M - 1 - depth])
        # 하
        for c in range(M - 2 - depth, depth - 1, -1):
            nums[depth].append(arr[N - 1 - depth][c])
        # 좌
        for r in range(N - 2 - depth, depth, -1):
            nums[depth].append(arr[r][depth])
        depth += 1
    return nums


# 회전된 위치에 값 업데이트 (classify_array_by_depth 함수와 동일하게 상 -> 우 -> 하 -> 좌 순으로 진행)
def rotate_array():
    depth = 0
    while depth < depth_count:
        num = 0
        # 상
        for c in range(depth, M - depth):
            idx = (num + R) % len(classified_arr[depth])
            arr[depth][c] = classified_arr[depth][idx]
            num += 1
        # 우
        for r in range(depth + 1, N - depth):
            idx = (num + R) % len(classified_arr[depth])
            arr[r][M - 1 - depth] = classified_arr[depth][idx]
            num += 1
        # 하
        for c in range(M - 2 - depth, depth - 1, -1):
            idx = (num + R) % len(classified_arr[depth])
            arr[N - 1 - depth][c] = classified_arr[depth][idx]
            num += 1
        # 좌
        for r in range(N - 2 - depth, depth, -1):
            idx = (num + R) % len(classified_arr[depth])
            arr[r][depth] = classified_arr[depth][idx]
            num += 1
        depth += 1


N, M, R = map(int, sys.stdin.readline().strip().split())
arr = [sys.stdin.readline().strip().split() for _ in range(N)]
depth_count = min(N, M) // 2
classified_arr = classify_array_by_depth()
rotate_array()
print('\n'.join(' '.join(x) for x in arr))
