# 백준 25206 너의 평점은 - 실버 5
grades = {'A+': 4.5, 'A0': 4.0, 'B+': 3.5, 'B0': 3.0, 'C+': 2.5, 'C0': 2.0, 'D+': 1.5, 'D0': 1.0, 'F': 0.0}
subjects, total = 0, 0
for i in range(20):
    subject, score, grade = input().split()
    score = float(score)
    if grade != 'P':
        total += score * grades[grade]
        subjects += score

print(total / subjects)