# 백준 28114 팀명 정하기 - 브론즈 2

# 1. 입학연도 오름차순 정렬
# 2. 문제 많이 푼 사람 순 내림차순 정렬
import sys
input = sys.stdin.readline


def first_solution(years):
    years.sort()
    s = ''
    for i in range(3):
        s += str(years[i] % 100)
    return s


def second_solution(solved_people):
    solved_people.sort(reverse=True)
    s = ''
    for i in range(3):
        s += solved_people[i][1][0]
    return s


def main():
    years = list()
    solved_people = list()
    for _ in range(3):
        info = input().strip().split()
        years.append(int(info[1]))
        solved_people.append((int(info[0]), info[2]))
    print(first_solution(years))
    print(second_solution(solved_people))


main()