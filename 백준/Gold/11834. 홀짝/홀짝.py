# 백준 11834 홀짝 - 골드 2

# 각 층 별로 홀수와 짝수가 반복
# layer 1: 1 (1개) -> 1번째까지
# layer 2: 2, 4 (2개) -> 1+2 = 3번째까지
# layer 3: 5, 7, 9 (3개) -> 1+2+3 = 6번째까지
# layer 4: 10, 12, 14, 16 (4개) -> 1+2+3+4 = 10번째까지
# 내가 찾는 N번째 숫자가 일단 어떤 layer에 속해 있는지 찾고 해당 레이어에서 몇 번째 숫자인지 찾으면 된다.
# 이분탐색을 통해 layer 찾기 -> 원소의 개수가 N 이상이라면 일단 N번째 숫자보다 큰 숫자들이 존재한다는 것
# 그렇게 layer의 위치를 찾고 그 후에는 해당 레이어의 마지막 index - N을 해주어 차이가 얼마나 있는지 본다.
# 각 레이어의 수열은 공차가 2인 등차수열이므로 반복횟수 * 2(공차) 만큼 끝번째 숫자에서 빼줘서 정답 도출함 
import sys
input = sys.stdin.readline


def find_layer(N):
    layer, left, right = 0, 0, N
    while left <= right:
        mid = (left + right) // 2
        num_of_elements = (mid ** 2 + mid) // 2
        if num_of_elements >= N:
            layer = mid
            right = mid - 1
        else:
            left = mid + 1
    return layer


def find_val(N, layer):
    repeat = (layer ** 2 + layer) // 2 - N
    val = layer ** 2 - 2 * repeat
    return val


def main():
    N = int(input())
    return find_val(N, find_layer(N))


print(main())