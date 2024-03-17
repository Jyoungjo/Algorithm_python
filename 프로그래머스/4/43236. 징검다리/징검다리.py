# 프로그래머스 43236 징검다리 LV.4
def solution(distance, rocks, n):
    answer = 0
    left, right = 0, distance
    rocks.append(distance)
    rocks.sort()

    while left <= right:
        mid = (left + right) // 2
        pre_rock = 0
        delete_count = 0
        for rock in rocks:
            if rock - pre_rock < mid:
                delete_count += 1
            else:
                pre_rock = rock

            if delete_count > n:
                break

        if delete_count > n:
            right = mid - 1
        else:
            answer = mid
            left = mid + 1

    return answer