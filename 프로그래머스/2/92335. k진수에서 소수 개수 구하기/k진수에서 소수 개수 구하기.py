# 프로그래머스 92335 k진수에서 소수 개수 구하기


# k진수 변환 함수
def change_number(n, k):
    a = ''
    while n > 0:
        n, r = divmod(n, k)
        a += str(r)
    return a[::-1]


# 소수 판별
def check_prime(x):
    if x == 1:
        return False
    for i in range(2, int(x ** (1 / 2)) + 1):
        if x % i == 0:
            return False
    return True


def solution(n, k):
    answer = 0

    # k진수로 변환
    k_num = change_number(n, k)

    # 0을 기준으로 숫자 분리
    l = k_num.split('0')

    # 분리한 숫자 리스트 중에서 에라토스테네스 실행
    for x in l:
        if x and check_prime(int(x)):
            answer += 1
    return answer