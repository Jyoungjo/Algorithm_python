-- 대여 시작일 기준 2022-08 ~ 2022-10 총 대여 횟수 5 이상인 자동차들 중에서
-- 월별 자동차 ID별 총 대여 횟수 리스트 출력
-- 월 오름차순 / 자동차 ID 내림차순
-- 총 대여 횟수 0 -> 제외
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
    AND CAR_ID IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
        GROUP BY CAR_ID
        HAVING COUNT(*) >= 5
    )
GROUP BY MONTH, CAR_ID
HAVING NOT RECORDS = 0
ORDER BY MONTH, CAR_ID DESC