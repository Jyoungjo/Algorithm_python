-- 의사이름, 의사ID, 면허번호, 고용일자, 진료과코드, 전화번호
-- 이름, 아이디, 진료과, 고용일자 조회
-- 고용일자 내림차순 / 이름 오름차순
SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') AS HIRE_YMD
FROM DOCTOR
WHERE MCDP_CD IN ('CS', 'GS')
ORDER BY HIRE_YMD DESC, DR_NAME