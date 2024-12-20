-- 동물 ID, 종류, 보호 시작일, 시작 상태, 이름, 성별
-- 아픈 동물 ID, 이름 조회
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = 'Sick'
ORDER BY ANIMAL_ID
;