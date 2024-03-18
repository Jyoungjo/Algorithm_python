# 프로그래머스 258707 n + 1 카드게임 LV.3
from collections import deque
from itertools import combinations


def solution(coin, cards):
    answer = 1
    n = len(cards)
    cards = deque(cards)
    my_cards = list(cards.popleft() for _ in range(n // 3))
    keep_cards = list()
    
    while my_cards:
        for _ in range(2):
            keep_cards.append(cards.popleft())
        my_comb = [i for i in combinations(my_cards, 2)]
        keep_comb = [i for i in combinations(keep_cards, 2)]
        my_keep_comb = list((i, j) for i in my_cards for j in keep_cards)

        flag = True
        # 1. 내 카드 뭉치에서 두 장을 골라 n + 1 을 맞출 수 있는 경우
        # 1(1). 이 경우 카드 두 장을 버리고 다음 라운드 진행
        for m in my_comb:
            m1, m2 = m
            if m1 + m2 == n + 1:
                my_cards.remove(m1)
                my_cards.remove(m2)
                answer += 1
                flag = False
                break
        # 2. 내 카드 뭉치에서 한 장 + 공통 카드 뭉치에서 한 장 골라 n + 1 맞출 수 있는 경우
        # 2(1). 이 경우 코인 한 개를 소모한 뒤 카드 두 장을 버리고 다음 라운드 진행
        for m_k in my_keep_comb:
            m1, k1 = m_k
            if not flag:
                break
            if m1 + k1 == n + 1 and coin >= 1:
                my_cards.remove(m1)
                keep_cards.remove(k1)
                coin -= 1
                answer += 1
                flag = False
                break
        # 3. 공통 카드 뭉치에서 두 장 골라 n + 1 맞출 수 있는 경우
        # 3(1). 이 경우 코인 두 개를 소모한 뒤 카드 두 장을 버리고 다음 라운드 진행
        for k in keep_comb:
            k1, k2 = k
            if not flag:
                break
            if k1 + k2 == n + 1 and coin >= 2:
                keep_cards.remove(k1)
                keep_cards.remove(k2)
                coin -= 2
                answer += 1
                flag = False
                break
        # 4. 위 경우가 전부 안 된다면 게임 종료
        if flag:
            return answer

    return answer