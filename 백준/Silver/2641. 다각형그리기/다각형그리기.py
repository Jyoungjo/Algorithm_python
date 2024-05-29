# 백준 2641 다각형그리기 - 실버 2

# 모양수열의 원소에 따라 방향이 결정
# 같다는 조건은 방향이 반대로 그려져도 결국 같은 모양을 완성하면 된다. -> 시작점이 바뀌어도 모양을 만들어내면 그만
# 표본과 다른 것들을 비교
# 표본, 방향 반대 / 표본, 순서 다른 것
from collections import deque


# 방향이 반대인 경우 시작점은 같으나 진행이 반대이므로 reversed 해준다.
def change_direction(coord):
    c_d = []
    for x in coord:
        if x == 1:
            c_d.append(3)
        elif x == 3:
            c_d.append(1)
        elif x == 2:
            c_d.append(4)
        else:
            c_d.append(2)
    return reversed(c_d)


N = int(input())
sample = deque(map(int, input().split()))
sample_d_changed = deque(change_direction(sample))
K = int(input())

answer = []
for _ in range(K):
    target = deque(map(int, input().split()))

    for _ in range(N):
        if target == sample or target == sample_d_changed:
            answer.append(' '.join(map(str, target)))
        sample.append(sample.popleft())
        sample_d_changed.append(sample_d_changed.popleft())

print(len(answer))
for a in answer:
    print(a)