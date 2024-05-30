# 1. 한 줄에 5개 이상의 연속된 자음이 포함되면 안 됨
# 2. 한 줄에 4개 이상의 연속된 자음을 포함하는 단어가 있고, 
#    사용자가 지난 10개의 매세지 중 이런 줄을 2번 이상 보냈으면 안 됨
# 3. 사용자가 지난 10개의 메세지 중 동일한 줄을 2번 이상 보냈으면 안 됨
# -> 각각 함수로 구현

# 최근 10줄은 유저가 보냈으나 채팅창에 보내지지 않은 메시지까지 포함

# * 반례
# input().strip()으로 채팅 데이터를 가져왔는데
# 채팅이
# "Hello"
# "Hello "
# 이런 형태일 경우, 두 채팅을 같은 채팅으로 확인해 오답

CONSONANTS = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ"

N = int(input().strip())
answers = ['y'] * N
check4Result = [False] * N
chats = [""] * N
for i in range(N):
    chats[i] = input()

def check5(line):
    count = 0
    for c in line:
        if c in CONSONANTS:
            count += 1
            if count > 5:
                return True
        else:
            count = 0
    return False

def check4(line, index):
    words = line.split()
    count = 0

    for word in words:
        for c in word:
            if c in CONSONANTS:
                count += 1
                if count > 4:
                    check4Result[index] = True
                    break
            else:
                count = 0
    
    if check4Result[index] and check4Result[max(0, index - 10) : index].count(True) > 2:
        return True 
    else:
        return False
    
def checkTwice(line, index):
    if chats[max(0, index - 10):index].count(line) > 1: return True
    else: return False

for i, line in enumerate(chats):
    if check5(line) or check4(line, i) or checkTwice(line, i):
        answers[i] = 'n'


print('\n'.join(answers))