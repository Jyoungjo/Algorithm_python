# 선물 주고 받은 기록 있다 -> 선물 더 많이 준 사람이 +1
# 없거나 같다면? -> 선물 지수 큰 사람이 작은 사람에게 +1
# 선물 지수 = 자신이 준 선물 총 개수 - 받은 선물 총 개수
# 선물 지수까지 같으면 선물 주고 받지 않는다.
# 목표 : 선물을 가장 많이 받을 친구의 받을 선물의 수

def solution(friends, gifts):
    answer = 0
    next_gift, d_gifts, d_giver, d_receiver = {}, {}, {}, {}
    
    for f1 in friends:
        inner = {}
        for f2 in friends:
            if f1 == f2:
                continue

            inner[f2] = 0
        d_gifts[f1] = inner
        d_giver[f1] = 0
        d_receiver[f1] = 0
        next_gift[f1] = 0
        
    for gift in gifts:
        giver, receiver = gift.split()
        d_gifts[giver][receiver] += 1
        d_giver[giver] += 1
        d_receiver[receiver] += 1
    
    for i in range(len(friends) - 1):
        for j in range(i + 1, len(friends)):
            A = friends[i]
            B = friends[j]
            
            A_count = d_gifts[A][B]
            B_count = d_gifts[B][A]
            
            if A_count > B_count:
                next_gift[A] += 1
            elif B_count > A_count:
                next_gift[B] += 1
            else:
                A_gift_point = d_giver[A] - d_receiver[A]
                B_gift_point = d_giver[B] - d_receiver[B]
                
                if A_gift_point > B_gift_point:
                    next_gift[A] += 1
                elif B_gift_point > A_gift_point:
                    next_gift[B] += 1
        
    return max(next_gift.values())