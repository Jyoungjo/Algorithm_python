# 프로그래머스 1845 폰켓몬 LV.1
def solution(nums):
    max = int(len(nums) / 2)
    s_nums = set(nums)
    if max <= len(s_nums):
        return max
    return len(s_nums)