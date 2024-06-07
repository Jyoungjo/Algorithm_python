# 백준 10815 숫자 카드 - 실버 5

# 찾아야 하는 카드 리스트를 set으로 만들어서 없으면 0 있으면 1 리턴하게 만들기
import sys
input = sys.stdin.readline


def find_card(my_cards, cards):
    return ''.join('1' if card in my_cards else '0' for card in cards)


def main():
    N = int(input())
    my_cards = set(input().split())
    M = int(input())
    cards = input().split()
    return find_card(my_cards, cards)


print(*main())