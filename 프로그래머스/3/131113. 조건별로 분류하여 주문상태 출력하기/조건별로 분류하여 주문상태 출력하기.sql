-- 2022-05-01 기준 주문ID, 제품ID, 출고일자, 출고여부 조회
-- 출고완료, 출고대기, 출고미정 / 주문ID 오름차순
SELECT ORDER_ID, PRODUCT_ID, SUBSTR(OUT_DATE, 1) AS OUT_DATE,
       CASE WHEN OUT_DATE <= DATE('2022-05-01') THEN '출고완료'
            WHEN OUT_DATE IS NULL THEN '출고미정'
            ELSE '출고대기'
            END AS 출고여부
FROM FOOD_ORDER
ORDER BY 1