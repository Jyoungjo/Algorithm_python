def solution(cards):
    is_opened = [False for _ in range(len(cards))]
    arr = []
    idx, cnt = 0, 0

    for i in range(len(cards)):
        if all(is_opened):
            break
        n = 1
        idx = i
        while True:
            is_opened[idx] = True
            next_idx = cards[idx] - 1

            if is_opened[next_idx]:
                arr.append(n)
                cnt += n
                break
            else:
                n += 1
                idx = next_idx
    arr.sort()
    
    if len(arr) == 1:
        return 0

    return arr[-1] * arr[-2]