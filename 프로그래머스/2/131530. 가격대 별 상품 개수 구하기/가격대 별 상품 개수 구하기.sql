-- 만원 단위의 가격대 별 상품 개수 출력
-- PRICE_GROUP, PRODUCTS 컬럼으로
-- 가격대 정보 = 각 구간 최소 금액 (만원 이상 ~ 2만원 미만 -> 만원) / 가격대 오름차순

SELECT CASE
       WHEN PRICE < 10000 THEN 0
       WHEN PRICE < 20000 THEN 10000
       WHEN PRICE < 30000 THEN 20000
       WHEN PRICE < 40000 THEN 30000
       WHEN PRICE < 50000 THEN 40000
       WHEN PRICE < 60000 THEN 50000
       WHEN PRICE < 70000 THEN 60000
       WHEN PRICE < 80000 THEN 70000
       WHEN PRICE < 90000 THEN 80000
       ELSE 90000 END AS PRICE_GROUP,
       COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY 1