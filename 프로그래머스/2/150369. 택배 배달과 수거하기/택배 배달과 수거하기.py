def solution(cap, n, deliveries, pickups):
    answer = 0
    deliveries = deliveries[::-1]
    pickups = pickups[::-1]

    delivery, pickup = 0, 0

    for i in range(n):
        delivery += deliveries[i]
        pickup += pickups[i]

        while delivery > 0 or pickup > 0:
            delivery -= cap
            pickup -= cap
            answer += n - i

    return answer * 2