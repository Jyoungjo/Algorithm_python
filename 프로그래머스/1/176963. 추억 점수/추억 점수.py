# 프로그래머스 176963 추억 점수 LV.1
def solution(name, yearning, photo):
    answer = []
    n_y = dict(zip(name, yearning)) # 이름, 그리움 순서대로 딕셔너리 생성

    for g in photo:
        sum = 0 # 각 리스트별 점수 합산 변수
        for p in g:
            # 딕셔너리 안에 사진 속 인물이 있다면 sum 에 더해준다.
            if p in n_y:
                sum += n_y[p]

        answer.append(sum) # 한 리스트 끝나면 점수 입력

    return answer