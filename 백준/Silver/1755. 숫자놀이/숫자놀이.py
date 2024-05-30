# 백준 1755 숫자놀이 - 실버 4

# M 이상 N 이하의 정수에서 숫자를 따로따로 읽었을 때, 사전순으로 정렬하여 출력하기
# 우선 각 숫자에 대한 배열 생성 -> 각 숫자 영어 변경 -> 사전순 정렬 후 출력
import sys


M, N = list(map(int, sys.stdin.readline().split()))
alpha = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
nums = [i for i in range(M, N+1)]
answer = []
for i in range(len(nums)):
    tmp = []
    for j in str(nums[i]):
        tmp.append(alpha[int(j)])
    answer.append((nums[i], ' '.join(tmp)))

answer_sorted = sorted(answer, key=lambda x: x[1])
numbers = [f'{num}' for num, _ in answer_sorted]

for i in range(0, len(numbers), 10):
    print(' '.join(numbers[i:i+10]))