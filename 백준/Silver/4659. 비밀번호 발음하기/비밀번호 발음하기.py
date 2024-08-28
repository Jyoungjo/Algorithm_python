# 백준 4659 비밀번호 발음하기 -

# 모음 하나 이상 포함
# 모음 3개 혹은 자음 3개 연속 X
# 같은 글자 연속 두번 X -> ee, oo 허용

import sys
input = sys.stdin.readline


def solution():
    vowels = ('a', 'e', 'i', 'o', 'u')
    while True:
        current = input().split('\n')[0]
        if current == 'end':
            break

        flag = True
        stack = []
        for i in range(len(current)):
            is_vowel = False
            if current[i] in vowels:
                is_vowel = True

            if not stack:
                stack.append((current[i], is_vowel))
                continue

            if len(stack) > 0 and stack[-1][0] == current[i]:
                if stack[-1][0] != 'e' and stack[-1][0] != 'o':
                    flag = False
                    break
                else:
                    stack.append((current[i], is_vowel))
            elif len(stack) > 1 and (stack[-1][1] == stack[-2][1] == is_vowel == True or
                                   stack[-1][1] == stack[-2][1] == is_vowel == False):
                flag = False
                break
            else:
                stack.append((current[i], is_vowel))

        if all(not stack[i][1] for i in range(len(stack))):
            flag = False

        if flag:
            print(f'<{current}> is acceptable.')
        else:
            print(f'<{current}> is not acceptable.')


solution()