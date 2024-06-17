# 백준 1713 후보 추천하기 - 실버 1

# 사진틀에 사진 넣기
# 비어있으면 그냥 넣고 아니라면 삭제(추천 수 제일 낮은 순, 동일하면 오래된 순)
# 기존에 있으면 추천 수 증가, 삭제되는 경우에는 추천 수 0 변경
import sys
input = sys.stdin.readline

CNT, ORDER = 'cnt', 'order'


def find_min_val(student_number, student_information, min_cnt, min_order, target):
    if student_information[CNT] < min_cnt:
        min_cnt = student_information[CNT]
        min_order = student_information[ORDER]
        target = student_number
    elif student_information[CNT] == min_cnt and student_information[ORDER] < min_order:
        min_order = student_information[ORDER]
        target = student_number
    return min_cnt, min_order, target


def delete_student(recommend_dict):
    MAX_VALUE = float('inf')
    min_cnt, min_order, target = MAX_VALUE, MAX_VALUE, 0
    for s_num, info in recommend_dict.items():
        min_cnt, min_order, target = find_min_val(s_num, info, min_cnt, min_order, target)
    del recommend_dict[target]


def solution(N, recommends):
    idx = 0
    recommend_dict = dict()
    for student_num in recommends:
        if student_num not in recommend_dict:
            if len(recommend_dict) == N:
                delete_student(recommend_dict)
            recommend_dict[student_num] = {CNT: 1, ORDER: idx}
            idx += 1
        else:
            recommend_dict[student_num][CNT] += 1
    return sorted(recommend_dict.keys())


def main():
    N = int(input())
    total = int(input())
    recommends = list(map(int, input().split()))
    return print(*solution(N, recommends))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()