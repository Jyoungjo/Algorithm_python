# 백준 13335 트럭 - 실버 1

# 트럭이 지나가는 상황 구현하는 문제
# 다리에 트럭이 진입할 때, 내 앞에 트럭과 내가 다리의 무게를 견딜 수 있으면 내가 진입
# 그렇지 않으면 내 앞의 트럭이 다리를 지날 때까지 기다려야함.
# queue 써서 트럭 순서대로 진입하게 하고 다리 무게와 진입해야 할 트럭 무게를 L과 비교해서 진입 여부 판별하기
import sys
from collections import deque
input = sys.stdin.readline


def cal_min_time(w, L, trucks):
    time = 0
    bridge = deque([0] * w)
    bridge_weight = 0
    while len(trucks) != 0:
        bridge_weight -= bridge.popleft()
        if bridge_weight + trucks[0] <= L:
            bridge_weight += trucks[0]
            bridge.append(trucks.popleft())
        else:
            bridge.append(0)
        time += 1
    return time + w


def main():
    n, w, L = map(int, input().split())
    trucks = deque(list(map(int, input().split())))
    return print(cal_min_time(w, L, trucks))


main()