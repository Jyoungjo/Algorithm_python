# 프로그래머스 42584 주식가격 LV.2
def solution(prices):
    from collections import deque
    answer = []
    prices = deque(prices)
    while prices:
        seconds = 0
        price = prices.popleft()

        for p in prices:
            if price <= p:
                seconds += 1
            else:
                seconds += 1
                break
        answer.append(seconds)
    return answer