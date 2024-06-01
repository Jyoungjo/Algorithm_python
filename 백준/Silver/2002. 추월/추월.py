# 백준 2002 추월 - 실버 1

# 추월했다의 기준은? -> 터널에 들어갔을 때의 idx를 나오는 차들에게 지정해주고 
# 나왔을 때 차들을 순회하면서 내 뒤에 하나라도 나보다 작은 idx 가 있다면 무조건 추월한 것임
import sys
from collections import defaultdict


N = int(sys.stdin.readline().strip())
entered_cars = defaultdict(int)
for i in range(N):
    entered_cars[sys.stdin.readline().strip()] = i
exited_cars = []
for i in range(N):
    car = sys.stdin.readline().strip()
    exited_cars.append((car, entered_cars[car]))

cnt = 0
for i in range(N):
    out_car, idx = exited_cars[i]
    if any(idx > other_idx for _, other_idx in exited_cars[i+1:]):
        cnt += 1
print(cnt)