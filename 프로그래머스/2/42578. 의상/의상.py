# 프로그래머스 42578 의상 LV.2
from functools import reduce
from collections import Counter

def solution(clothes):
    cnt = Counter([kind for name, kind in clothes])
    answer = reduce(lambda x, y: x*(y+1), cnt.values(), 1) - 1
    return answer