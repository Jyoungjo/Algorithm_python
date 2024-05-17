from itertools import product


def solution(users, emoticons):
    answer = [0, 0]
    discount = [10, 20, 30, 40]
    for dc in product(discount, repeat=len(emoticons)):
        discount_prices = [(d, (e * (1 - (d / 100)))) for e, d in zip(emoticons, dc)]
        res = [0, 0]
        for user in users:
            percent, price = user
            buying = 0
            for d_p in discount_prices:
                if d_p[0] >= percent:
                    buying += d_p[1]
                    if buying >= price:
                        res[0] += 1
                        buying = 0
                        break
            res[1] += buying
        if answer[0] < res[0] or (answer[0] == res[0] and answer[1] < res[1]):
            answer = res
    return answer