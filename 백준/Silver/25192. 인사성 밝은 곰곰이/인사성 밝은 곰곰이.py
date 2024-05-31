# 백준 25192 인사성 밝은 곰곰이 - 실버 4

# 입장하고 난 뒤 처음 채팅은 반드시 이모티콘
# 그 외에 채팅은 일반 채팅이므로 count X -> set으로 처리
# 다음 엔터 나오기 전까지 set에 이름 담아둠
# ENTER 가 입력되면 set의 길이만큼 answer에 더해주고 set.clear
# 마지막까지 진행하면 set에 남은 것들 기록하고 종료
import sys


N = int(sys.stdin.readline())
answer = 0
log = set()
for i in range(N):
    s = sys.stdin.readline().strip()
    if s != 'ENTER':
        log.add(s)
    else:
        answer += len(log)
        log.clear()
answer += len(log)
print(answer)