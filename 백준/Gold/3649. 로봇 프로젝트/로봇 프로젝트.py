# 백준 3649 로봇 프로젝트 - 골드 5

# 조각들을 양 옆에서부터 투 포인터로 순회하면서 조각의 합이 x에 적합한지 확인하기
# 정렬시켜서 양 옆에서 찾으면 정답이 여러 개인 경우의 최댓값을 구할 수 있음
import sys
input = sys.stdin.readline


def block_the_hall(x, pieces):
    left, right = 0, len(pieces) - 1
    while left < right:
        sum = pieces[left] + pieces[right]
        if sum == x:
            return pieces[left], pieces[right]
        elif sum > x:
            right -= 1
        else:
            left += 1
    return None


def main():
    while True:
        try:
            x = int(input()) * 10000000
            n = int(input())
            pieces = sorted([int(input()) for _ in range(n)])
            ans = block_the_hall(x, pieces)
            print(f'yes {ans[0]} {ans[1]}') if ans else print('danger')
        except:
            break


main()