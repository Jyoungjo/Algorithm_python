-- 2022년 10월 16일에 대여중인 자동차 = 대여중, 대여중이지 않으면 대여 가능 표시 컬럼 추가해서
-- 자동차 ID, 대여 가능 여부 리스트 출력
-- 반납 날짜가 2022년 10월 16일이면 대여중 / 자동차 ID 기준 내림차순

-- 1. END_DATE별 대여중인지 아닌지 그룹화

SELECT CAR_ID, 
       CASE
       WHEN MAX('2022-10-16' BETWEEN START_DATE AND END_DATE) = 1 THEN '대여중'
       ELSE '대여 가능'
       END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC