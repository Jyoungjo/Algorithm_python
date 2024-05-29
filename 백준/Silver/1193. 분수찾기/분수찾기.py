# 백준 1193 분수 찾기 - 실버 5

# 지그재그 규칙에 따라 주어진 숫자의 인덱스의 분수를 찾는 문데
# 각 라인 별로 규칙 존재
# 홀수 라인이면 분자 1씩 감소, 분모 1씩 증가
# 짝수 라인이면 분자 1씩 증가, 분모 1씩 감소
# 몇 번째 라인의 어느 위치에 있는지 알면 분수 찾기 가능

num = int(input())
line, end = 0, 0

# 해당 라인의 끝 숫자는 전 라인의 끝 값 + 해당 라인
# 끝 숫자보다 입력값이 작다면 해당 라인 범위 안에 들어온 것이므로 while 문 종료
while num > end:
    line += 1
    end += line

# 입력값 번째의 인덱스 찾기
diff = end - num

# 짝수
if line % 2 == 0:
    a = line - diff
    b = diff + 1
# 홀수
elif line % 2 == 1:
    a = diff + 1
    b = line - diff

print(f'{a}/{b}')