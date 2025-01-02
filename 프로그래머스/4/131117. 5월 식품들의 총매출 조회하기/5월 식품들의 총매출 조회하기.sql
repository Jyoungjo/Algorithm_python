-- 생산일자 2022년 5월 식품들의 식품 ID, 식품 이름, 총 매출 조회
-- 총 매출 내림차순 / ID 오름차순

SELECT O.PRODUCT_ID, P.PRODUCT_NAME, SUM(P.PRICE * O.AMOUNT) AS TOTAL_SALES
FROM FOOD_ORDER AS O
    JOIN FOOD_PRODUCT AS P
    ON P.PRODUCT_ID = O.PRODUCT_ID
WHERE O.PRODUCE_DATE LIKE '2022-05%'
GROUP BY PRODUCT_ID
ORDER BY 3 DESC, 1