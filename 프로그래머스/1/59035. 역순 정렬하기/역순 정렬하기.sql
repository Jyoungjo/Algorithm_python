-- 동물 ID, 생물 종, 보호 시작일, 보호 시작 상태, 이름, 성별 및 중성화 여부
-- 동물 이름, 보호 시작일 조회
-- 동물 ID 내림차순
SELECT NAME, DATETIME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID DESC
;