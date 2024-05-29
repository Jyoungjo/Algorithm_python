# 백준 1924 2007년 - 브론즈 1
thirty_one = [1,3,5,7,8,10,12]
thirty = [4,6,9,11]
week = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT']
x, y = map(int, input().split())
day = 0
for i in range(1, x):
    if i in thirty_one:
        day += 31
    elif i in thirty:
        day += 30
    else:
        day += 28
day += y

print(week[day % 7])