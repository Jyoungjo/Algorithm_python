from itertools import combinations, product
from bisect import bisect_left


def solution(dice):
    win_combi = {}
    total = len(dice)
    for A_dice in combinations(range(total), total // 2):
        B_dice = tuple(i for i in range(total) if i not in A_dice)
        
        A_dice_sum, B_dice_sum = [], []
        for prod in product(range(6), repeat=total // 2):
            A_dice_sum.append(sum(dice[i][j] for i, j in zip(A_dice, prod)))
            B_dice_sum.append(sum(dice[i][j] for i, j in zip(B_dice, prod)))
        
        B_dice_sum.sort()
        wins = sum(bisect_left(B_dice_sum, A) for A in A_dice_sum)
        win_combi[wins] = list(A_dice)
        
    max_key = max(win_combi.keys())
    
    return [x + 1 for x in win_combi[max_key]]