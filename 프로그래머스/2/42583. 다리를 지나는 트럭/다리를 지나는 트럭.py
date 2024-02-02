# 프로그래머스 42583 다리를 지나는 트럭 LV.2
def solution(bridge_length, weight, truck_weights):
    from collections import deque
    answer = 0
    bridge = deque([0] * bridge_length)
    trucks = deque(truck_weights)

    current_truck = 0
    while len(trucks) != 0:
        current_truck -= bridge.popleft()

        if current_truck + trucks[0] <= weight:
            current_truck += trucks[0]
            bridge.append(trucks.popleft())
        else:
            bridge.append(0)
        answer += 1

    return answer + bridge_length