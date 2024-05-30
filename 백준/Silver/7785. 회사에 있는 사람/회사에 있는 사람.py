# 백준 7785 회사에 있는 사람 - 실버 5

# 어떤 사람이 회사에 들어왔는지 나갔는지 기록
# 현재 회사에 남아있는 사람 누구인지
import sys
from collections import defaultdict


n = int(sys.stdin.readline())
logs = defaultdict(str)
for i in range(n):
    info = sys.stdin.readline().split()
    logs[info[0]] = info[1]

print('\n'.join(sorted([k for k, v in logs.items() if v == 'enter'], reverse=True)))