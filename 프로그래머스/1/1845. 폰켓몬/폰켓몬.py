# 프로그래머스 1845 폰켓몬 LV.1

def solution(nums):
    return min(len(nums) / 2, len(set(nums)))