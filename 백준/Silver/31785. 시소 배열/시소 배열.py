# 백준 31785 시소 배열 - 실버 1

# 질문에 따라 처리해야 하는 행동이 달라진다.
# 1이 나오면 숫자 삽입 / 2 나오면 반 갈라서 합이 작은 배열 삭제
import sys


Q = int(sys.stdin.readline())
arr = []
for i in range(Q):
    question = list(map(int, sys.stdin.readline().split()))
    if question[0] == 1:
        arr.append(question[1])
    else:
        N = len(arr)
        left, right = sum(arr[:N//2]), sum(arr[N//2:])
        if left <= right:
            arr = arr[N//2:]
            print(left)
        else:
            arr = arr[:N//2]
            print(right)
print(' '.join(map(str, arr)))