def solution(s):
    s = s.title()
    result = []

    for i in range(len(s)):
        if i > 0 and '0' <= s[i-1] <= '9' and s[i].isupper():
            result.append(s[i].lower())
        else:
            result.append(s[i])

    return ''.join(result)