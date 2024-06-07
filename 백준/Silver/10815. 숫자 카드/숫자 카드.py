# 백준 10815 숫자 카드 - 실버 5

# 내가 가진 카드에서 찾아야 하는 카드가 있는지 탐색 진행
# N = 최대 500000 -> N^2 = 2500억 -> 최대 O(NlogN) = 이분 탐색
import sys
input = sys.stdin.readline


def find_card(my_cards, card_list):
    return [binary_search(my_cards, card) for card in card_list]


def binary_search(arr, target):
    s, e = 0, len(arr) - 1
    while s <= e:
        mid = (s + e) // 2
        if arr[mid] == target:
            return 1
        elif arr[mid] > target:
            e = mid - 1
        else:
            s = mid + 1
    return 0


def main():
    N = int(input())
    my_cards = sorted(list(map(int, input().split())))
    M = int(input())
    card_list = list(map(int, input().split()))
    return find_card(my_cards, card_list)


print(*main())