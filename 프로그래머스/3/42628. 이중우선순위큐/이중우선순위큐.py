# 프로그래머스 42628 이중우선순위큐 LV.3
import heapq

def solution(operations):
    op_list = [(operation.split()[0], int(operation.split()[1])) for operation in operations]
    heap = []
    for command, num in op_list:
        if command == 'I':
            heapq.heappush(heap, num)
        elif command == 'D' and len(heap) != 0:
            if num == 1:
                heap.remove(max(heap))
            else:
                heapq.heappop(heap)

    if len(heap) == 0:
        return [0, 0]
    else:
        return [max(heap), min(heap)]